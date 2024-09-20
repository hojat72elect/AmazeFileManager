

package com.amaze.filemanager.filesystem.compressed.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.tukaani.xz.DeltaOptions;
import org.tukaani.xz.FinishableWrapperOutputStream;
import org.tukaani.xz.UnsupportedOptionsException;

class DeltaDecoder extends CoderBase {
  DeltaDecoder() {
    super(Number.class);
  }

  @Override
  InputStream decode(
      final String archiveName,
      final InputStream in,
      final long uncompressedLength,
      final Coder coder,
      final byte[] password,
      final int maxMemoryLimitInKb)
      throws IOException {
    return new DeltaOptions(getOptionsFromCoder(coder)).getInputStream(in);
  }

  @SuppressWarnings("resource")
  @Override
  OutputStream encode(final OutputStream out, final Object options) throws IOException {
    final int distance = numberOptionOrDefault(options, 1);
    try {
      return new DeltaOptions(distance).getOutputStream(new FinishableWrapperOutputStream(out));
    } catch (final UnsupportedOptionsException ex) { // NOSONAR
      throw new IOException(ex.getMessage());
    }
  }

  @Override
  byte[] getOptionsAsProperties(final Object options) {
    return new byte[] {(byte) (numberOptionOrDefault(options, 1) - 1)};
  }

  @Override
  Object getOptionsFromCoder(final Coder coder, final InputStream in) {
    return getOptionsFromCoder(coder);
  }

  private int getOptionsFromCoder(final Coder coder) {
    if (coder.properties == null || coder.properties.length == 0) {
      return 1;
    }
    return (0xff & coder.properties[0]) + 1;
  }
}

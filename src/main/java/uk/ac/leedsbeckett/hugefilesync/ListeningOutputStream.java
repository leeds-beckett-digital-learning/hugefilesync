/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.hugefilesync;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author jon
 */
public class ListeningOutputStream extends OutputStream
{
  final SyncHugeFilesListener listener;
  final OutputStream wrapped;
  final long expectedlength;
  long sent;
  long reported;
  
  final static long INTERVAL = 1024L * 1024L;
  
  public ListeningOutputStream( SyncHugeFilesListener listener, OutputStream wrapped, long expectedlength )
  {
    this.listener       = listener;
    this.wrapped        = wrapped;
    this.expectedlength = expectedlength;
    sent = 0L;
    reported = 0L;
  }
  
  void report()
  {
    if ( listener == null || expectedlength < 1L )
      return;
    
    long unreported = sent - reported;
    if ( unreported < INTERVAL )
      return;
    reported = sent;
    
    double percent = 100.0 * (double)sent / (double)expectedlength;
    int progress = (int) Math.round( percent );
    listener.syncHugeFilesPartProgress( progress, 100, "Downloaded " + progress + "%"  );
  }
  
  @Override
  public void write(int b) throws IOException
  {
    wrapped.write( b );
    sent++;
    report();
  }

  @Override
  public void write(byte[] b, int off, int len) throws IOException
  {
    wrapped.write( b, off, len );
    sent+=len;
    report();
  }

  @Override
  public void write(byte[] b) throws IOException
  {
    wrapped.write(b);
    sent+=b.length;
    report();
  }

  @Override
  public void close() throws IOException
  {
    wrapped.close();
  }

  @Override
  public void flush() throws IOException
  {
    wrapped.flush();
  }
    
  
}

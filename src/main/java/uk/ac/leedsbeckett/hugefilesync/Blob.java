/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.hugefilesync;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jon
 */
public class Blob
{
  final String blobdigest;
  final Path relativepath;
  final Path localpath;
  final URI remotebase;
  final Path sourcepath;
  URI uri= null;
  private final ArrayList<FileEntry> files = new ArrayList<>();

  public Blob( Path localbase, URI remotebase, Path sourcedir, String blobdigest )
  {
    this.blobdigest = blobdigest;
    Path temp = Paths.get( "blobs" );
    temp = temp.resolve( blobdigest.substring( 0, 2 ) );
    temp = temp.resolve( blobdigest.substring( 0, 4 ) );
    relativepath = temp.resolve( "blob_" + blobdigest + ".bin" );
    localpath = localbase.resolve( relativepath );
    this.remotebase = remotebase;
    this.sourcepath = (sourcedir==null)?null:sourcedir.resolve( relativepath );
  }

  public void addFileEntry( FileEntry f )
  {
    if ( remotebase != null && uri == null )
      uri = remotebase.resolve( f.source );    
    files.add( f );
  }
 
  public List<FileEntry> getFileEntries()
  {
    return files;
  }
}

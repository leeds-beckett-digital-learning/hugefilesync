/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.hugefilesync;

/**
 *
 * @author jon
 */
public class FileEntry
{
  final String source;
  final String path;
  

  public FileEntry( String source, String path )
  {
    this.source = source;
    this.path = path;
  }
}

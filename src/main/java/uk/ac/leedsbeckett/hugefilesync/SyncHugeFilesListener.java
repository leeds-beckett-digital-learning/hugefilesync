/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.hugefilesync;

/**
 *
 * @author jon
 */
public interface SyncHugeFilesListener
{
  public void syncHugeFilesStarted();
  public void syncHugeFilesStopped( boolean success );
  public void syncHugeFilesTotalProgress( int n, int of, String message );
  public void syncHugeFilesPartProgress( int n, int of, String message );
  public void syncHugeFilesLog( String message );
}

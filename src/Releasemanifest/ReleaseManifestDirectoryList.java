package Releasemanifest;

import LittleEndian.LeWord;
import Util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: philipp.hentschel
 * Date: 16.07.14
 * Time: 17:06
 * To change this template use File | Settings | File Templates.
 */
public class ReleaseManifestDirectoryList {

    private long direcoryListCount;
    private byte[] content = null;
    private long offsetDirectoryList;
    private  List<ReleaseManifestDirectoryEntry> directoryEntries;
    private ReleasemanifestFile relFile;

    public ReleaseManifestDirectoryList(ReleasemanifestFile releasemanifestFile, List<Byte> filebyteList, long fileheaderlocation) {
         this.relFile = releasemanifestFile;
        this.content = ArrayUtils.objectArrayToByteArray(filebyteList.toArray());
        this.offsetDirectoryList = offsetDirectoryList;
        this.direcoryListCount = new LeWord(filebyteList,4).getContent();

        long offsetEntryStart = offsetDirectoryList+4;
        this.directoryEntries = new ArrayList<ReleaseManifestDirectoryEntry>();
        int entrynum = 0;

        for(long currentOffset = offsetEntryStart; currentOffset < offsetEntryStart +20*this.direcoryListCount;currentOffset += 20)
        {
            this.directoryEntries.add(new ReleaseManifestDirectoryEntry(relFile,filebyteList,currentOffset,entrynum++));
        }


    }
}

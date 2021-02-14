package com.integrals.chordlinesapp.Helper;

public class AlbumModel {
    private String CoverPic;
    private String YouTubeLink;
    private String PublishedON;
    private String AlbumName;
    private String Details;
    private String DownloadLinkLow;
    private String DownloadLinkMedium;
    private String DownloadLinkHigh;
    private String DownloadLinkAudio;

    public AlbumModel(String coverPic, String youTubeLink,
                      String publishedON,
                      String albumName,
                      String details,
                      String downloadLinkLow,
                      String downloadLinkMedium,
                      String downloadLinkHigh,
                      String downloadLinkAudio) {
        CoverPic = coverPic;
        YouTubeLink = youTubeLink;
        PublishedON = publishedON;
        AlbumName = albumName;
        Details = details;
        DownloadLinkLow = downloadLinkLow;
        DownloadLinkMedium = downloadLinkMedium;
        DownloadLinkHigh = downloadLinkHigh;
        DownloadLinkAudio = downloadLinkAudio;
    }

    public String getCoverPic() {
        return CoverPic;
    }

    public void setCoverPic(String coverPic) {
        CoverPic = coverPic;
    }

    public String getYouTubeLink() {
        return YouTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        YouTubeLink = youTubeLink;
    }

    public String getPublishedON() {
        return PublishedON;
    }

    public void setPublishedON(String publishedON) {
        PublishedON = publishedON;
    }

    public String getAlbumName() {
        return AlbumName;
    }

    public void setAlbumName(String albumName) {
        AlbumName = albumName;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getDownloadLinkLow() {
        return DownloadLinkLow;
    }

    public void setDownloadLinkLow(String downloadLinkLow) {
        DownloadLinkLow = downloadLinkLow;
    }

    public String getDownloadLinkMedium() {
        return DownloadLinkMedium;
    }

    public void setDownloadLinkMedium(String downloadLinkMedium) {
        DownloadLinkMedium = downloadLinkMedium;
    }

    public String getDownloadLinkHigh() {
        return DownloadLinkHigh;
    }

    public void setDownloadLinkHigh(String downloadLinkHigh) {
        DownloadLinkHigh = downloadLinkHigh;
    }

    public String getDownloadLinkAudio() {
        return DownloadLinkAudio;
    }

    public void setDownloadLinkAudio(String downloadLinkAudio) {
        DownloadLinkAudio = downloadLinkAudio;
    }
}
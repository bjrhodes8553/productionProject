package production;

/**
 * *********************************************************
 * File : AudioPlayer.Java
 * Author: Breanna
 * Rhodes
 * Class : COP 3003
 * Purpose : This class extends the abstract class, Product, and implements
 * the interface MultimediaControl.
 * **********************************************************
 */
public class AudioPlayer extends Product implements MultimediaControl {
  // Variables that will be used in this class are declared.
  String supportedAudioFormats;
  String supportedPlaylistFormats;
  String name;
  String manufacturer;
  ItemType type;

  // Constructor for the AudioPlayer than uses the super's contructor in its parameters.
  AudioPlayer(
      String manufacturer,
      String name,
      String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(manufacturer, name, ItemType.AUDIO);
    this.supportedPlaylistFormats = supportedPlaylistFormats;
    this.supportedAudioFormats = supportedAudioFormats;
  }

  // This constructor only takes the name, manufacturer and type as arguments.
  AudioPlayer(String manufacturer, String name, ItemType type) {
    super(manufacturer, name, ItemType.AUDIO);
  }

  // Getter methods:
  public String getSupportedAudioFormats() {
    return supportedAudioFormats;
  }

  public String getSupportedPlaylistFormats() {
    return supportedPlaylistFormats;
  }

  // Setter methods:
  public void setSupportedAudioFormats(String supportedAudioFormats) {
    this.supportedAudioFormats = supportedAudioFormats;
  }

  public void setSupportedPlaylistFormats(String supportedPlaylistFormats) {
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  // Methods that will describe the current state of the AudioPlayer.
  public void play() {
    System.out.println("Playing");
  }

  public void stop() {
    System.out.println("Stopping");
  }

  public void next() {
    System.out.println("Next");
  }

  public void previous() {
    System.out.println("Previous");
  }

  /**
   * METHOD NAME: toString PURPOSE: The purpose of this method is to take audio player object and
   * print the details of it to a string.
   */
  public String toString() {
    System.out.print(super.toString());
    return ("\nSupported Audio Formats: "
        + this.supportedAudioFormats
        + "\nSupported Playlist Formats: "
        + this.supportedPlaylistFormats);
  }
}

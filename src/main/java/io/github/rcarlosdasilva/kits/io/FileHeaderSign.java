package io.github.rcarlosdasilva.kits.io;

/**
 * 文件头标识
 * <p>
 * 参考 http://www.garykessler.net/library/file_sigs.html
 * 
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public enum FileHeaderSign {

  /**
   * JEPG.
   */
  JPEG("FFD8FF"),

  /**
   * PNG.
   */
  PNG("89504E47"),

  /**
   * GIF.
   */
  GIF("47494638"),

  /**
   * TIFF.
   */
  TIFF("49492A00"),

  /**
   * Windows Bitmap.
   */
  BMP("424D"),

  /**
   * CAD.
   */
  DWG("41433130"),

  /**
   * Adobe Photoshop.
   */
  PSD("38425053"),

  /**
   * Rich Text Format.
   */
  RTF("7B5C727466"),

  /**
   * XML.
   */
  XML("3C3F786D6C"),

  /**
   * HTML.
   */
  HTML("3c21"),

  /**
   * Email [thorough only].
   */
  EML("44656C69766572792D646174653A"),

  /**
   * Outlook Express.
   */
  DBX("CFAD12FEC5FD746F"),

  /**
   * Outlook (pst).
   */
  PST("2142444E"),

  /**
   * MS Word/Excel.
   */
  XLS_DOC("D0CF11E0"),

  /**
   * MS Access.
   */
  MDB("5374616E64617264204A"),

  /**
   * WordPerfect.
   */
  WPD("FF575043"),

  /**
   * Postscript.
   */
  EPS("252150532D41646F6265"),

  /**
   * Adobe Acrobat.
   */
  PDF("255044462D312E"),

  /**
   * Quicken.
   */
  QDF("AC9EBD8F"),

  /**
   * Windows Password.
   */
  PWL("E3828596"),

  /**
   * ZIP Archive.
   */
  ZIP("504B0304"),

  /**
   * RAR Archive.
   */
  RAR("52617221"),

  /**
   * Wave.
   */
  WAV("57415645"),

  /**
   * AVI.
   */
  AVI("41564920"),

  /**
   * Real Audio.
   */
  RAM("2E7261FD"),

  /**
   * Real Media.
   */
  RM("2E524D46"),

  /**
   * MPEG (mpg).
   */
  MPG("000001BA"),

  /**
   * MP3.
   */
  MP3("494433"),

  /**
   * MP4.
   */
  MP4("66747970"),

  /**
   * Quicktime.
   */
  MOV("6D6F6F76"),

  /**
   * FLV.
   */
  FLV("464C5601"),

  /**
   * Microsoft Windows Media Audio/Video File.
   */
  WMAV("3026B2758E66CF11"),

  /**
   * MIDI.
   */
  MID("4D546864");

  private String value;

  private FileHeaderSign(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public static FileHeaderSign byValue(String value) {
    for (FileHeaderSign sign : values()) {
      if (value.toUpperCase().startsWith(sign.getValue())) {
        return sign;
      }
    }
    return null;
  }

}

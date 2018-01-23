package io.github.rcarlosdasilva.kits.io;

import com.google.common.base.Strings;

/**
 * 文件签名
 * <p>
 * 内容参考 http://www.garykessler.net/library/file_sigs.html<br>
 * 数据从TrID_sigs_GCK转换而来，参考https://www.garykessler.net/software/index.html#filesigs
 * <p>
 * <p>
 * 修改：
 * <ol>
 * <li>合并所有文件头重复的扩展名
 * <li>删除所有none扩展名
 * <li>删除 FILE_NUM_429 非正常的扩展名
 * <li>删除 FILE_NUM_127 过长的pattern，而且扩展名不常用
 * <li>修改 FILE_NUM_267 缩短pattern，原pattern:
 * 4D6963726F736F66742057696E646F7773204D6564696120506C61796572202D2D20
 * <li>修改 FILE_NUM_335 缩短pattern，原pattern:
 * 53494D504C4520203D202020202020202020202020202020202020202054
 * <li>修改 FILE_NUM_6 缩短pattern，原pattern:
 * 000000006231050009000000002000000009000000000000
 * <li>修改 FILE_NUM_126 缩短pattern，原pattern:
 * 3C3F786D6C2076657273696F6E3D22312E30223F3E
 * </ol>
 * 最长pattern的长度为38字符
 *
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public enum FileSignatures {

  /**
   * 1Password 4 Cloud Keychain
   */
  FILE_NUM_274("4F50434C444154", 0, "attachment"),
  /** 1Password 4 Cloud Keychain encrypted data */
  // FILE_NUM_410("6F70646174613031", 0, "(none)"),
  /** 3GPP multimedia files */
  /**
   * 3rd Generation Partnership Project 3GPP
   */
  FILE_NUM_4("0000001466747970", 0, "3GG|3GP|3G2"),
  /** 3rd Generation Partnership Project 3GPP2 */
  /**
   * 3GPP2 multimedia files
   */
  FILE_NUM_11("0000002066747970", 0, "3GG|3GP|3G2"),
  /**
   * 7-Zip compressed file
   */
  FILE_NUM_118("377ABCAF271C", 0, "7Z"),
  /**
   * Access Data FTK evidence
   */
  FILE_NUM_449("A90D000000000000", 0, "DAT"),
  /**
   * Acronis True Image
   */
  FILE_NUM_456("B46E6844", 0, "TIB"),
  /**
   * Adaptive Multi-Rate ACELP Codec (GSM)
   */
  FILE_NUM_91("2321414D52", 0, "AMR"),
  /**
   * Adobe encapsulated PostScript
   */
  FILE_NUM_461("C5D0D3C6", 0, "EPS"),
  /**
   * Adobe FrameMaker
   */
  FILE_NUM_129("3C4D616B65724669", 0, "FM|MIF"),
  /** BizTalk XML-Data Reduced Schema */
  /**
   * Advanced Stream Redirector
   */
  FILE_NUM_122("3C", 0, "ASX|XDR"),
  /**
   * Agent newsreader character map
   */
  FILE_NUM_273("4E616D653A20", 0, "COD"),
  /**
   * AIN Compressed Archive
   */
  FILE_NUM_85("2112", 0, "AIN"),
  /**
   * Allegro Generic Packfile (compressed)
   */
  FILE_NUM_414("736C6821", 0, "DAT"),
  /**
   * Allegro Generic Packfile (uncompressed)
   */
  FILE_NUM_415("736C682E", 0, "DAT"),
  /**
   * Amiga disk file
   */
  FILE_NUM_179("444F53", 0, "ADF"),
  /**
   * Amiga DiskMasher compressed archive
   */
  FILE_NUM_178("444D5321", 0, "DMS"),
  /**
   * Amiga icon
   */
  FILE_NUM_492("E310000100000000", 0, "INFO"),
  /**
   * Antenna data file
   */
  FILE_NUM_317("5245564E554D3A2C", 0, "AD"),
  /** AOL address book */
  /**
   * AOL user configuration
   */
  FILE_NUM_141("414F4C4442", 0, "ABY|IDX"),
  /**
   * AOL address book index
   */
  FILE_NUM_144("414F4C494E444558", 0, "ABI"),
  /**
   * AOL and AIM buddy list
   */
  FILE_NUM_140("414F4C2046656564", 0, "BAG"),
  /**
   * AOL ART file_1
   */
  FILE_NUM_217("4A47030E", 0, "JG"),
  /**
   * AOL ART file_2
   */
  FILE_NUM_218("4A47040E", 0, "JG"),
  /**
   * AOL client preferences|settings file
   */
  FILE_NUM_143("414F4C494458", 0, "IND"),
  /**
   * AOL config files
   */
  FILE_NUM_139("414F4C", 0, "ABI|ABY|BAG|IDX|IND|PFC"),
  /**
   * AOL history|typed URL files
   */
  FILE_NUM_486("D42A", 0, "ARL|AUT"),
  /**
   * AOL HTML mail
   */
  FILE_NUM_124("3C21646F63747970", 0, "DCI"),
  /** AOL parameter|info files */
  // FILE_NUM_137("41435344", 0, "(none)"),
  /**
   * AOL personal file cabinet
   */
  FILE_NUM_145("414F4C564D313030", 0, "ORG|PFC"),
  /**
   * Apple audio and video
   */
  FILE_NUM_10("00000020667479704D3441", 0, "M4A"),
  /**
   * Apple Core Audio File
   */
  FILE_NUM_383("63616666", 0, "CAF"),
  /**
   * Apple Lossless Audio Codec file
   */
  FILE_NUM_393("667479704D344120", 4, "M4A"),
  /**
   * Approach index file
   */
  FILE_NUM_47("0300000041505052", 0, "ADX"),
  /**
   * Audacity audio file
   */
  FILE_NUM_388("646E732E", 0, "AU"),
  /** Audio Interchange File */
  /**
   * DAKX Compressed Audio
   */
  FILE_NUM_195("464F524D00", 0, "AIFF|DAX"),
  /** Audition graphic filter */
  /** Acrobat plug-in */
  /**
   * DirectShow filter
   */
  FILE_NUM_263("4D5A900003000000", 0, "FLT|API|AX"),
  /**
   * AVG6 Integrity database
   */
  FILE_NUM_146("415647365F496E74", 0, "DAT"),
  /**
   * BASE85 file
   */
  FILE_NUM_131("3C7E363C5C255F30675371683B", 0, "B85"),
  /**
   * Better Portable Graphics
   */
  FILE_NUM_154("425047FB", 0, "BPG"),
  /**
   * BGBlitz position database file
   */
  FILE_NUM_453("ACED000573720012", 0, "PDB"),
  /** Binary property list (plist) */
  // FILE_NUM_382("62706C697374", 0, "(none)"),
  /**
   * BinHex 4 Compressed Archive
   */
  FILE_NUM_100("2854686973206669", 0, "HQX"),
  /** BIOS details in RAM */
  // FILE_NUM_34("001400000102", 0, "(none)"),
  /**
   * Bitcoin Core wallet.dat file
   */
  FILE_NUM_6("000000006231050009000000002000", 8, "DAT"),
  /**
   * Bitcoin-Qt blockchain block file
   */
  FILE_NUM_508("F9BEB4D9", 0, "DAT"),
  /** BitLocker boot sector (Vista) */
  // FILE_NUM_499("EB52902D4656452D", 0, "(none)"),
  /** BitLocker boot sector (Win7) */
  // FILE_NUM_500("EB58902D4656452D", 0, "(none)"),
  /**
   * Bitmap image
   */
  FILE_NUM_152("424D", 0, "BMP|DIB"),
  /**
   * Blink compressed archive
   */
  FILE_NUM_157("426C696E6B", 0, "BLI"),
  /**
   * Brother-Babylock-Bernina Home Embroidery
   */
  FILE_NUM_94("2350454330303031", 0, "PEC"),
  /**
   * Brother-Babylock-Bernina Home Embroidery
   */
  FILE_NUM_95("2350455330", 0, "PES"),
  /**
   * bzip2 compressed archive
   */
  FILE_NUM_155("425A68", 0, "BZ2|TAR.BZ2|TBZ2|TB2"),
  /**
   * Calculux Indoor lighting project file
   */
  FILE_NUM_172("43616C63756C757820496E646F6F7220", 0, "CIN"),
  /**
   * CALS raster bitmap
   */
  FILE_NUM_417("737263646F636964", 0, "CAL"),
  /**
   * Canon RAW file
   */
  FILE_NUM_208("49491A0000004845", 0, "CRW"),
  /**
   * CD Stomper Pro label file
   */
  FILE_NUM_248("4D56", 0, "DSN"),
  /**
   * Cerius2 file
   */
  FILE_NUM_88("2320", 0, "MSI"),
  /**
   * ChromaGraph Graphics Card Bitmap
   */
  FILE_NUM_285("504943540008", 0, "IMG"),
  /**
   * COM+ Catalog
   */
  FILE_NUM_165("434F4D2B", 0, "CLB"),
  /**
   * Compressed archive file
   */
  FILE_NUM_379("60EA", 0, "ARJ"),
  /**
   * Compressed archive file
   */
  FILE_NUM_74("1A0B", 0, "PAK"),
  /**
   * Compressed archive
   */
  FILE_NUM_102("2D6C68", 2, "LHA|LZH"),
  /**
   * Compressed ISO CD image
   */
  FILE_NUM_162("4349534F", 0, "CSO"),
  /**
   * Compressed tape archive_1
   */
  FILE_NUM_82("1F9D90", 0, "TAR.Z"),
  /**
   * Compressed tape archive_2
   */
  FILE_NUM_83("1FA0", 0, "TAR.Z"),
  /**
   * Compucon-Singer embroidery design file
   */
  FILE_NUM_16("00000000000000000000000000000000", 0, "XXX"),
  /**
   * Corel Binary metafile
   */
  FILE_NUM_164("434D5831", 0, "CLB"),
  /**
   * Corel color palette
   */
  FILE_NUM_490("DCDC", 0, "CPL"),
  /**
   * Corel Paint Shop Pro image
   */
  FILE_NUM_430("7E424B00", 0, "PSP"),
  /**
   * Corel Photopaint file_1
   */
  FILE_NUM_167("4350543746494C45", 0, "CPT"),
  /**
   * Corel Photopaint file_2
   */
  FILE_NUM_168("43505446494C45", 0, "CPT"),
  /** cpio archive */
  // FILE_NUM_113("3037303730", 0, "(none)"),
  /**
   * Creative Voice
   */
  FILE_NUM_175("437265617469766520566F6963652046", 0, "VOC"),
  /**
   * Crush compressed archive
   */
  FILE_NUM_170("43525553482076", 0, "CRU"),
  /**
   * Csound music
   */
  FILE_NUM_128("3C43736F756E6453796E74686573697A", 0, "CSD"),
  /**
   * Dalvik (Android) executable file
   */
  FILE_NUM_387("6465780A", 0, "dex"),
  /**
   * DAX Compressed CD image
   */
  FILE_NUM_176("44415800", 0, "DAX"),
  /**
   * DB2 conversion file
   */
  FILE_NUM_341("53514C4F434F4E56", 0, "CNV"),
  /** dBASE III file */
  /**
   * MapInfo Native Data Format
   */
  FILE_NUM_45("03", 0, "DB3|DAT"),
  /**
   * dBASE IV file
   */
  FILE_NUM_48("04", 0, "DB4"),
  /**
   * dBASE IV or dBFast configuration file
   */
  FILE_NUM_54("08", 0, "DB"),
  /**
   * DesignTools 2D Design file
   */
  FILE_NUM_53("0764743264647464", 0, "DTD"),
  /**
   * DeskMate Document
   */
  FILE_NUM_61("0D444F43", 0, "DOC"),
  /**
   * DeskMate Worksheet
   */
  FILE_NUM_63("0E574B53", 0, "WKS"),
  /**
   * Developer Studio subheader
   */
  FILE_NUM_518("FDFFFFFF20", 512, "OPT"),
  /**
   * Dial-up networking file
   */
  FILE_NUM_369("5B50686F6E655D", 0, "DUN"),
  /**
   * Digital Speech Standard file
   */
  FILE_NUM_43("02647373", 0, "DSS"),
  /**
   * Digital Watchdog DW-TP-500G audio
   */
  FILE_NUM_431("7E742C015070024D52", 0, "IMG"),
  /**
   * DOS system driver
   */
  FILE_NUM_542("FFFFFFFF", 0, "SYS"),
  /**
   * Dreamcast audio
   */
  FILE_NUM_434("80000020031204", 0, "ADX"),
  /**
   * DST Compression
   */
  FILE_NUM_185("44535462", 0, "DST"),
  /**
   * DVD video file
   */
  FILE_NUM_14("000001BA", 0, "MPG|VOB"),
  /** DVD info file */
  /**
   * DVR-Studio stream file
   */
  FILE_NUM_180("445644", 0, "IFO|DVR"),
  /**
   * Easy CD Creator 5 Layout file
   */
  FILE_NUM_65("10000000", 0, "CL5"),
  /**
   * EasyRecovery Saved State file
   */
  FILE_NUM_184("4552465353415645", 0, "DAT"),
  /**
   * eFax file
   */
  FILE_NUM_491("DCFE", 0, "EFX"),
  /** ELF executable */
  // FILE_NUM_432("7F454C46", 0, "(none)"),
  /**
   * Elite Plus Commander game file
   */
  FILE_NUM_182("454C49544520436F", 0, "CDR"),
  /**
   * Encapsulated PostScript file
   */
  FILE_NUM_97("252150532D41646F", 0, "EPS"),
  /**
   * EnCase case file
   */
  FILE_NUM_378("5F434153455F", 0, "CAS|CBK"),
  /**
   * EnCase Evidence File Format V2
   */
  FILE_NUM_188("455646320D0A81", 0, "Ex01"),
  /**
   * EndNote Library File
   */
  FILE_NUM_134("40404020000040404040", 32, "ENL"),
  /**
   * Excel spreadsheet subheader_1
   */
  FILE_NUM_55("0908100000060500", 512, "XLS"),
  /**
   * Excel spreadsheet subheader_2
   */
  FILE_NUM_515("FDFFFFFF10", 512, "XLS"),
  /**
   * Excel spreadsheet subheader_3
   */
  FILE_NUM_517("FDFFFFFF1F", 512, "XLS"),
  /**
   * Excel spreadsheet subheader_4
   */
  FILE_NUM_519("FDFFFFFF22", 512, "XLS"),
  /**
   * Excel spreadsheet subheader_5
   */
  FILE_NUM_520("FDFFFFFF23", 512, "XLS"),
  /**
   * Excel spreadsheet subheader_6
   */
  FILE_NUM_521("FDFFFFFF28", 512, "XLS"),
  /**
   * Excel spreadsheet subheader_7
   */
  FILE_NUM_522("FDFFFFFF29", 512, "XLS"),
  /**
   * Exchange e-mail
   */
  FILE_NUM_360("582D", 0, "EML"),
  /**
   * Expert Witness Compression Format
   */
  FILE_NUM_187("455646090D0AFF00", 0, "E01"),
  /** Extended tcpdump (libpcap) capture file */
  // FILE_NUM_448("A1B2CD34", 0, "(none)"),
  /**
   * eXtensible ARchive file
   */
  FILE_NUM_424("78617221", 0, "XAR"),
  /** FAT12 File Allocation Table */
  // FILE_NUM_504("F0FFFF", 0, "(none)"),
  /** FAT16 File Allocation Table */
  // FILE_NUM_505("F8FFFFFF", 0, "(none)"),
  /** FAT32 File Allocation Table_1 */
  // FILE_NUM_506("F8FFFF0FFFFFFF0F", 0, "(none)"),
  /** FAT32 File Allocation Table_2 */
  // FILE_NUM_507("F8FFFF0FFFFFFFFF", 0, "(none)"),
  /**
   * Fiasco database definition file
   */
  FILE_NUM_192("4644424800", 0, "FDB"),
  /**
   * Firebird and Interbase database files
   */
  FILE_NUM_37("01003930", 0, "FDB|GDB"),
  /**
   * Flash video file
   */
  FILE_NUM_194("464C56", 0, "FLV"),
  /**
   * Flexible Image Transport System (FITS) file
   */
  FILE_NUM_335("53494D504C4520203D202020202020", 0, "FITS"),
  /**
   * FLIC animation
   */
  FILE_NUM_33("0011", 0, "FLI"),
  /**
   * Flight Simulator Aircraft Configuration
   */
  FILE_NUM_373("5B666C7473696D2E", 0, "CFG"),
  /**
   * Free Lossless Audio Codec file
   */
  FILE_NUM_391("664C614300000022", 0, "FLAC"),
  /**
   * FreeArc compressed file
   */
  FILE_NUM_148("41724301", 0, "ARC"),
  /**
   * Fuzzy bitmap (FBM) file
   */
  FILE_NUM_99("256269746D6170", 0, "FBM"),
  /**
   * GEM Raster file
   */
  FILE_NUM_498("EB3C902A", 0, "IMG"),
  /**
   * Generic AutoCAD drawing
   */
  FILE_NUM_135("41433130", 0, "DWG"),
  /**
   * Generic drawing programs
   */
  FILE_NUM_51("07", 0, "DRW"),
  /**
   * Generic e-mail_1
   */
  FILE_NUM_328("52657475726E2D50", 0, "EML"),
  /**
   * Generic e-mail_2
   */
  FILE_NUM_198("46726F6D", 0, "EML"),
  /**
   * Genetec video archive
   */
  FILE_NUM_202("47656E65746563204F6D6E6963617374", 0, "G64"),
  /**
   * GIF file
   */
  FILE_NUM_199("47494638", 0, "GIF"),
  /**
   * GIMP file
   */
  FILE_NUM_399("67696d7020786366", 0, "XCF"),
  /**
   * GIMP pattern file
   */
  FILE_NUM_200("47504154", 0, "PAT"),
  /**
   * GNU Info Reader file
   */
  FILE_NUM_347("5468697320697320", 0, "INFO"),
  /**
   * GPG public keyring
   */
  FILE_NUM_444("99", 0, "GPG"),
  /**
   * GPS Exchange (v1.1)
   */
  FILE_NUM_130("3C6770782076657273696F6E3D22312E", 0, "GPX"),
  /**
   * Hamarsoft compressed archive
   */
  FILE_NUM_440("91334846", 0, "HAP"),
  /**
   * Harvard Graphics presentation file
   */
  FILE_NUM_204("4848474231", 0, "SH3"),
  /**
   * Harvard Graphics presentation
   */
  FILE_NUM_333("53484F57", 0, "SHW"),
  /**
   * Harvard Graphics symbol graphic
   */
  FILE_NUM_138("414D594F", 0, "SYW"),
  /** Huskygram */
  // FILE_NUM_429("Poem", CSD, " or Singer embroidery"),
  /**
   * Husqvarna Designer
   */
  FILE_NUM_376("5DFCC800", 0, "HUS"),
  /**
   * IE History file
   */
  FILE_NUM_174("436C69656E742055", 0, "DAT"),
  /**
   * Img Software Bitmap
   */
  FILE_NUM_331("53434D49", 0, "IMG"),
  /** INFO2 Windows recycle bin_1 */
  // FILE_NUM_49("04000000", 0, "(none)"),
  /** INFO2 Windows recycle bin_2 */
  // FILE_NUM_50("05000000", 0, "(none)"),
  /**
   * Inno Setup Uninstall Log
   */
  FILE_NUM_214("496E6E6F20536574", 0, "DAT"),
  /**
   * Install Shield compressed file
   */
  FILE_NUM_211("49536328", 0, "CAB|HDR"),
  /**
   * Intel PROset|Wireless Profile
   */
  FILE_NUM_386("64000000", 0, "P10"),
  /**
   * Inter@ctive Pager Backup (BlackBerry file
   */
  FILE_NUM_215("496E7465724063746976652050616765", 0, "IPD"),
  /**
   * ISO Base Media file (MPEG-4) v1
   */
  FILE_NUM_395("6674797069736F6D", 4, "MP4"),
  /**
   * ISO-9660 CD Disc Image
   */
  FILE_NUM_160("4344303031", 0, "ISO"),
  /**
   * Jar archive
   */
  FILE_NUM_377("5F27A889", 0, "JAR"),
  /**
   * JARCS compressed archive
   */
  FILE_NUM_216("4A4152435300", 0, "JAR"),
  /**
   * Java archive_2
   */
  FILE_NUM_302("504B030414000800", 0, "JAR"),
  /**
   * Java bytecode
   */
  FILE_NUM_463("CAFEBABE", 0, "CLASS"),
  /**
   * Java Cryptography Extension keystore
   */
  FILE_NUM_465("CECECECE", 0, "JCEKS"),
  /** Java serialization data */
  // FILE_NUM_452("ACED", 0, "(none)"),
  /** JavaKeyStore */
  // FILE_NUM_526("FEEDFEED", 0, "(none)"),
  /**
   * JBOG2 image file
   */
  FILE_NUM_443("974A42320D0A1A0A", 0, "JB2"),
  /**
   * Jeppesen FliteLog file
   */
  FILE_NUM_462("C8007900", 0, "LBK"),
  /**
   * JPEG2000 image files
   */
  FILE_NUM_1("0000000C6A502020", 0, "JP2"),
  /**
   * JPEG|EXIF|SPIFF images
   */
  FILE_NUM_535("FFD8FF", 0, "JFIF|JPE|JPEG|JPG"),
  /**
   * Keyboard driver file
   */
  FILE_NUM_533("FF4B455942202020", 0, "SYS"),
  /**
   * KGB archive
   */
  FILE_NUM_220("4B47425F61726368", 0, "KGB"),
  /**
   * Kodak Cineon image
   */
  FILE_NUM_435("802A5FD7", 0, "CIN"),
  /** KWAJ (compressed) file */
  // FILE_NUM_222("4B57414A88F027D1", 0, "(none)"),
  /**
   * LH archive (old vers.|type 1)
   */
  FILE_NUM_69("1A02", 0, "ARC"),
  /**
   * LH archive (old vers.|type 2)
   */
  FILE_NUM_70("1A03", 0, "ARC"),
  /**
   * LH archive (old vers.|type 3)
   */
  FILE_NUM_71("1A04", 0, "ARC"),
  /**
   * LH archive (old vers.|type 4)
   */
  FILE_NUM_72("1A08", 0, "ARC"),
  /**
   * LH archive (old vers.|type 5)
   */
  FILE_NUM_73("1A09", 0, "ARC"),
  /**
   * Logical File Evidence Format
   */
  FILE_NUM_227("4C5646090D0AFF00", 0, "E01"),
  /**
   * Lotus 1-2-3 (v1)
   */
  FILE_NUM_18("0000020006040600", 0, "WK1"),
  /**
   * Lotus 1-2-3 (v3)
   */
  FILE_NUM_19("00001A0000100400", 0, "WK3"),
  /**
   * Lotus 1-2-3 (v4|v5)
   */
  FILE_NUM_20("00001A0002100400", 0, "WK4|WK5"),
  /**
   * Lotus 1-2-3 (v9)
   */
  FILE_NUM_21("00001A00051004", 0, "123"),
  /**
   * Lotus AMI Pro document_1
   */
  FILE_NUM_370("5B5645525D", 0, "SAM"),
  /**
   * Lotus AMI Pro document_2
   */
  FILE_NUM_375("5B7665725D", 0, "SAM"),
  /**
   * Lotus Notes database
   */
  FILE_NUM_68("1A0000040000", 0, "NSF"),
  /**
   * Lotus Notes database template
   */
  FILE_NUM_67("1A0000", 0, "NTF"),
  /**
   * Lotus WordPro file
   */
  FILE_NUM_359("576F726450726F", 0, "LWP"),
  /**
   * MacOS X image file
   */
  FILE_NUM_423("7801730D626260", 0, "DMG"),
  /**
   * Macromedia Shockwave Flash
   */
  FILE_NUM_366("5A5753", 0, "SWF"),
  /**
   * MapInfo Interchange Format file
   */
  FILE_NUM_353("56657273696F6E20", 0, "MIF"),
  /**
   * MapInfo Sea Chart
   */
  FILE_NUM_84("21", 0, "BSB"),
  /**
   * MAr compressed archive
   */
  FILE_NUM_231("4D41723000", 0, "MAR"),
  /**
   * Matroska stream file
   */
  FILE_NUM_77("1A45DFA393428288", 0, "MKV"),
  /** Mbox table of contents file */
  // FILE_NUM_32("000DBBA0", 0, "(none)"),
  /**
   * Merriam-Webster Pocket Dictionary
   */
  FILE_NUM_228("4D2D5720506F636B", 0, "PDB"),
  /**
   * Micrografx vector graphic file
   */
  FILE_NUM_42("01FF02040302", 0, "DRW"),
  /**
   * Microsoft Access 2007
   */
  FILE_NUM_27("000100005374616E6461726420414345204442", 0, "ACCDB"),
  /**
   * Microsoft Access
   */
  FILE_NUM_28("000100005374616E64617264204A6574204442", 0, "MDB"),
  /** Microsoft cabinet file */
  /** MS Access Snapshot Viewer file */
  /**
   * Powerpoint Packaged Presentation
   */
  FILE_NUM_240("4D534346", 0, "CAB|SNP|PPZ"),
  /**
   * Microsoft Code Page Translation file
   */
  FILE_NUM_372("5B57696E646F7773", 0, "CPX"),
  /**
   * Microsoft Money file
   */
  FILE_NUM_26("000100004D534953414D204461746162617365", 0, "MNY"),
  /** Microsoft Office document */
  /** Microsoft Installer package */
  /** Microsoft Common Console Document */
  /** Minitab data file */
  /** Lotus|IBM Approach 97 file */
  /** Developer Studio File Options file */
  /** CaseWare Working Papers */
  /** Access project file */
  /** Visual Studio Solution User Options file */
  /** Visio file */
  /** SPSS output file */
  /** Revit Project file */
  /** MS Publisher file */
  /** MSWorks database file */
  /**
   * MSWorks text document
   */
  FILE_NUM_470("D0CF11E0A1B11AE1", 0,
      "DOC|DOT|PPS|PPT|XLA|XLS|WIZ|MSI|MSC|MTW|APR|OPT|AC_|ADP|SOU|VSD|SPO|RVT|PUB|DB|WPS"),
  /**
   * Microsoft Outlook Exchange Offline Storage Folder
   */
  FILE_NUM_87("2142444E", 0, "OST"),
  /**
   * Microsoft Windows Imaging Format
   */
  FILE_NUM_244("4D5357494D", 0, "WIM"),
  /**
   * Microsoft Windows User State Migration Tool
   */
  FILE_NUM_309("504D4F43434D4F43", 0, "PMOCCMOC"),
  /**
   * Microsoft|MSN MARC archive
   */
  FILE_NUM_230("4D415243", 0, "MAR"),
  /** MIDI sound file */
  /**
   * Yamaha Piano
   */
  FILE_NUM_246("4D546864", 0, "MID|MIDI|PCS"),
  /**
   * Milestones project management file
   */
  FILE_NUM_234("4D494C4553", 0, "MLS"),
  /**
   * Milestones project management file_1
   */
  FILE_NUM_249("4D56323134", 0, "MLS"),
  /**
   * Milestones project management file_2
   */
  FILE_NUM_250("4D563243", 0, "MLS"),
  /** MMC Snap-in Control file */
  // FILE_NUM_127(
  // "3C3F786D6C2076657273696F6E3D22312E30223F3E0D0A3C4D4D435F436F6E736F6C6546696C6520436F6E736F6C6556657273696F6E3D22",
  // 0, "MSC"),
  /**
   * Monochrome Picture TIFF bitmap
   */
  FILE_NUM_60("0CED", 0, "MP"),
  /**
   * Mozilla archive
   */
  FILE_NUM_229("4D41523100", 0, "MAR"),
  /**
   * MP3 audio file
   */
  FILE_NUM_206("494433", 0, "MP3"),
  /**
   * MPEG video file
   */
  FILE_NUM_13("000001B3", 0, "MPG"),
  /**
   * MPEG-2 AAC audio
   */
  FILE_NUM_537("FFF9", 0, "AAC"),
  /**
   * MPEG-4 AAC audio
   */
  FILE_NUM_536("FFF1", 0, "AAC"),
  /**
   * MPEG-4 v1
   */
  FILE_NUM_3("000000146674797069736F6D", 0, "MP4"),
  /**
   * MPEG-4 video file_1
   */
  FILE_NUM_392("6674797033677035", 4, "MP4"),
  /**
   * MPEG-4 video file_2
   */
  FILE_NUM_394("667479704D534E56", 4, "MP4"),
  /**
   * MPEG-4 video_1
   */
  FILE_NUM_7("0000001866747970", 0, "3GP5|M4V|MP4"),
  /**
   * MPEG-4 video_2
   */
  FILE_NUM_8("0000001C66747970", 0, "MP4"),
  /**
   * MPEG-4 video|QuickTime file
   */
  FILE_NUM_396("667479706D703432", 4, "M4V"),
  /**
   * MS Agent Character file
   */
  FILE_NUM_460("C3ABCDAB", 0, "ACS"),
  /**
   * MS Answer Wizard
   */
  FILE_NUM_439("8A0109000000E108", 0, "AW"),
  /**
   * MS C++ debugging symbols file
   */
  FILE_NUM_265("4D6963726F736F667420432F432B2B20", 0, "PDB"),
  /**
   * MS COFF relocatable object code
   */
  FILE_NUM_224("4C01", 0, "OBJ"),
  /**
   * MS Compiled HTML Help File
   */
  FILE_NUM_213("49545346", 0, "CHI|CHM"),
  /**
   * MS Developer Studio project file
   */
  FILE_NUM_90("23204D6963726F73", 0, "DSP"),
  /**
   * MS Document Imaging file
   */
  FILE_NUM_186("4550", 0, "MDI"),
  /**
   * MS Exchange configuration file
   */
  FILE_NUM_367("5B47656E6572616C", 0, "ECF"),
  /**
   * MS Fax Cover Sheet
   */
  FILE_NUM_191("464158434F564552", 0, "CPE"),
  /**
   * MS Office 2007 documents
   */
  FILE_NUM_301("504B030414000600", 0, "DOCX|PPTX|XLSX"),
  /** MS Office Open XML Format Document */
  /** OpenDocument template */
  /** OpenOffice documents */
  /** PKZIP archive_1 */
  /** StarOffice spreadsheet */
  /** Mozilla Browser Archive */
  /** MacOS X Dashboard Widget */
  /** KWord document */
  /** Java archive_1 */
  /** Google Earth session file */
  /** eXact Packager Models */
  /** XML paper specification file */
  /**
   * Windows Media compressed skin file
   */
  FILE_NUM_288("504B0304", 0,
      "DOCX|PPTX|XLSX|ODT|ODP|OTT|SXC|SXD|SXI|SXW|ZIP|SXC|XPI|KWD|JAR|KMZ|XPT|XPS|WMZ"),
  /**
   * MS OneNote note
   */
  FILE_NUM_494("E4525C7B8CD8A74D", 0, "ONE"),
  /**
   * MS Publisher
   */
  FILE_NUM_364("5854", 0, "BDR"),
  /**
   * MS Publisher file subheader
   */
  FILE_NUM_512("FDFFFFFF02", 512, "PUB"),
  /**
   * MS Publisher subheader
   */
  FILE_NUM_510("FD377A585A00", 512, "PUB"),
  /**
   * MS Reader eBook
   */
  FILE_NUM_212("49544F4C49544C53", 0, "LIT"),
  /**
   * MS security catalog file
   */
  FILE_NUM_109("30", 0, "CAT"),
  /**
   * MS Visual Studio workspace file
   */
  FILE_NUM_389("64737766696C65", 0, "DSW"),
  /**
   * MS Windows journal
   */
  FILE_NUM_270("4E422A00", 0, "JNT|JTP"),
  /**
   * MS WinMobile personal note
   */
  FILE_NUM_427("7B5C707769", 0, "PWI"),
  /**
   * MS Write file_1
   */
  FILE_NUM_114("31BE", 0, "WRI"),
  /**
   * MS Write file_2
   */
  FILE_NUM_115("32BE", 0, "WRI"),
  /**
   * MS Write file_3
   */
  FILE_NUM_458("BE000000AB", 0, "WRI"),
  /**
   * MSinfo file
   */
  FILE_NUM_541("FFFE23006C006900", 0, "MOF"),
  /**
   * MultiBit Bitcoin blockchain file
   */
  FILE_NUM_339("53505642", 0, "SPVB"),
  /**
   * MultiBit Bitcoin wallet file
   */
  FILE_NUM_59("0A166F72672E626974636F696E2E7072", 0, "WALLET"),
  /**
   * MultiBit Bitcoin wallet information
   */
  FILE_NUM_408("6D756C74694269742E696E666F", 0, "INFO"),
  /**
   * National Imagery Transmission Format file
   */
  FILE_NUM_272("4E49544630", 0, "NTF"),
  /**
   * National Transfer Format Map
   */
  FILE_NUM_112("30314F52444E414E", 0, "NTF"),
  /** NAV quarantined virus file */
  // FILE_NUM_464("CD20AAAA02000000", 0, "(none)"),
  /**
   * Nero CD compilation
   */
  FILE_NUM_62("0E4E65726F49534F", 0, "NRI"),
  /**
   * NES Sound file
   */
  FILE_NUM_271("4E45534D1A01", 0, "NSF"),
  /**
   * Netscape Communicator (v4) mail folder
   */
  FILE_NUM_35("001E849000000000", 0, "SNM"),
  /**
   * Netscape Navigator (v4) database
   */
  FILE_NUM_31("0006156100000002000004D200001000", 0, "DB"),
  /**
   * NeXT|Sun Microsystems audio file
   */
  FILE_NUM_107("2E736E64", 0, "AU"),
  /**
   * Norton Disk Doctor undo file
   */
  FILE_NUM_308("504E4349554E444F", 0, "DAT"),
  /**
   * Novell LANalyzer capture file
   */
  FILE_NUM_40("0110", 0, "TR1"),
  /** NTFS MFT (BAAD) */
  // FILE_NUM_149("42414144", 0, "(none)"),
  /** NTFS MFT (FILE) */
  // FILE_NUM_193("46494C45", 0, "(none)"),
  /**
   * Ogg Vorbis Codec compressed file
   */
  FILE_NUM_276("4F67675300020000", 0, "OGA|OGG|OGV|OGX"),
  /**
   * OLE|SPSS|Visual C++ library file
   */
  FILE_NUM_243("4D53465402000100", 0, "TLB"),
  /**
   * Open Publication Structure eBook
   */
  FILE_NUM_299("504B03040A000200", 0, "EPUB"),
  /**
   * OpenEXR bitmap image
   */
  FILE_NUM_421("762F3101", 0, "EXR"),
  /** OS X ABI Mach-O binary (32-bit reverse) */
  // FILE_NUM_466("CEFAEDFE", 0, "(none)"),
  /** OS X ABI Mach-O binary (32-bit) */
  // FILE_NUM_524("FEEDFACE", 0, "(none)"),
  /** OS X ABI Mach-O binary (64-bit reverse) */
  // FILE_NUM_469("CFFAEDFE", 0, "(none)"),
  /** OS X ABI Mach-O binary (64-bit) */
  // FILE_NUM_525("FEEDFACF", 0, "(none)"),
  /**
   * Outlook address file
   */
  FILE_NUM_446("9CCBCB8D1375D211", 0, "WAB"),
  /**
   * Outlook Express address book (Win95)
   */
  FILE_NUM_436("813284C18505D011", 0, "WAB"),
  /**
   * Outlook Express e-mail folder
   */
  FILE_NUM_468("CFAD12FE", 0, "DBX"),
  /**
   * Packet sniffer files
   */
  FILE_NUM_361("58435000", 0, "CAP"),
  /**
   * Palm Address Book Archive
   */
  FILE_NUM_29("00014241", 0, "ABA"),
  /**
   * Palm DateBook Archive
   */
  FILE_NUM_30("00014244", 0, "DBA"),
  /**
   * Palm Desktop DateBook
   */
  FILE_NUM_459("BEBAFECA0F50616C6D53472044617461", 0, "DAT"),
  /**
   * Palm Zire photo database
   */
  FILE_NUM_177("44424648", 0, "DB"),
  /**
   * PalmOS SuperMemo
   */
  FILE_NUM_416("736D5F", 0, "PDB"),
  /**
   * Palmpilot resource file
   */
  FILE_NUM_153("424F4F4B4D4F4249", 0, "PRC"),
  /**
   * PathWay Map file
   */
  FILE_NUM_419("74424D504B6E5772", 60, "PRC"),
  /**
   * PAX password protected bitmap
   */
  FILE_NUM_282("504158", 0, "PAX"),
  /**
   * PCX bitmap
   */
  FILE_NUM_455("B168DE3A", 0, "DCX"),
  /**
   * PDF file
   */
  FILE_NUM_98("25504446", 0, "PDF|FDF"),
  /**
   * Perfect Office document
   */
  FILE_NUM_467("CF11E0A1B11AE100", 0, "DOC"),
  /**
   * PestPatrol data|scan strings
   */
  FILE_NUM_283("50455354", 0, "DAT"),
  /**
   * Pfaff Home Embroidery
   */
  FILE_NUM_116("3203100000000000000080000000FF00", 0, "PCS"),
  /**
   * PGP disk image
   */
  FILE_NUM_284("504750644D41494E", 0, "PGD"),
  /**
   * PGP public keyring
   */
  FILE_NUM_445("9901", 0, "PKR"),
  /**
   * PGP secret keyring_1
   */
  FILE_NUM_441("9500", 0, "SKR"),
  /**
   * PGP secret keyring_2
   */
  FILE_NUM_442("9501", 0, "SKR"),
  /**
   * Photoshop Custom Shape
   */
  FILE_NUM_385("6375736800000002", 0, "CSH"),
  /**
   * Photoshop image
   */
  FILE_NUM_120("38425053", 0, "PSD"),
  /**
   * PKLITE archive
   */
  FILE_NUM_305("504B4C495445", 30, "ZIP"),
  /**
   * PKSFX self-extracting archive
   */
  FILE_NUM_306("504B537058", 526, "ZIP"),
  /**
   * PKZIP archive_2
   */
  FILE_NUM_303("504B0506", 0, "ZIP"),
  /**
   * PKZIP archive_3
   */
  FILE_NUM_304("504B0708", 0, "ZIP"),
  /**
   * PNG image
   */
  FILE_NUM_438("89504E470D0A1A0A", 0, "PNG"),
  /**
   * Portable Graymap Graphic
   */
  FILE_NUM_279("50350A", 0, "PGM"),
  /**
   * PowerBASIC Debugger Symbols
   */
  FILE_NUM_418("737A657A", 0, "PDB"),
  /**
   * PowerPoint presentation subheader_1
   */
  FILE_NUM_36("006E1EF0", 512, "PPT"),
  /**
   * PowerPoint presentation subheader_2
   */
  FILE_NUM_64("0F00E803", 512, "PPT"),
  /**
   * PowerPoint presentation subheader_3
   */
  FILE_NUM_451("A0461DF0", 512, "PPT"),
  /**
   * PowerPoint presentation subheader_4
   */
  FILE_NUM_514("FDFFFFFF0E000000", 512, "PPT"),
  /**
   * PowerPoint presentation subheader_5
   */
  FILE_NUM_516("FDFFFFFF1C000000", 512, "PPT"),
  /**
   * PowerPoint presentation subheader_6
   */
  FILE_NUM_523("FDFFFFFF43000000", 512, "PPT"),
  /**
   * Psion Series 3 Database
   */
  FILE_NUM_275("4F504C4461746162", 0, "DBF"),
  /**
   * Puffer ASCII encrypted archive
   */
  FILE_NUM_156("426567696E20507566666572", 0, "APUF"),
  /**
   * Puffer encrypted archive
   */
  FILE_NUM_310("50554658", 0, "PUF"),
  /** QBASIC SZDD file */
  // FILE_NUM_342("535A2088F02733D1", 0, "(none)"),
  /**
   * Qcow Disk Image
   */
  FILE_NUM_312("514649", 0, "QEMU"),
  /**
   * Qimage filter
   */
  FILE_NUM_422("76323030332E3130", 0, "FLT"),
  /**
   * Quake archive file
   */
  FILE_NUM_280("5041434B", 0, "PAK"),
  /**
   * Quark Express (Intel)
   */
  FILE_NUM_22("00004949585052", 0, "QXD"),
  /**
   * Quark Express (Motorola)
   */
  FILE_NUM_23("00004D4D585052", 0, "QXD"),
  /**
   * Quatro Pro for Windows 7.0
   */
  FILE_NUM_132("3E000300FEFF090006", 24, "WB3"),
  /**
   * QuickBooks backup
   */
  FILE_NUM_190("458600000600", 0, "QBB"),
  /**
   * Quicken data file
   */
  FILE_NUM_314("5157205665722E20", 0, "ABD|QSD"),
  /**
   * Quicken data
   */
  FILE_NUM_450("AC9EBD8F0000", 0, "QDF"),
  /**
   * Quicken data
   */
  FILE_NUM_311("51454C20", 92, "QEL"),
  /**
   * Quicken price history
   */
  FILE_NUM_46("03000000", 0, "QPH"),
  /**
   * Quicken QuickFinder Information File
   */
  FILE_NUM_278("5000000020000000", 0, "IDX"),
  /**
   * QuickReport Report
   */
  FILE_NUM_531("FF0A00", 0, "QRP"),
  /**
   * QuickTime movie_1
   */
  FILE_NUM_402("6D6F6F76", 4, "MOV"),
  /**
   * QuickTime movie_2
   */
  FILE_NUM_403("66726565", 4, "MOV"),
  /**
   * QuickTime movie_3
   */
  FILE_NUM_404("6D646174", 4, "MOV"),
  /**
   * QuickTime movie_4
   */
  FILE_NUM_405("77696465", 4, "MOV"),
  /**
   * QuickTime movie_5
   */
  FILE_NUM_406("706E6F74", 4, "MOV"),
  /**
   * QuickTime movie_6
   */
  FILE_NUM_407("736B6970", 4, "MOV"),
  /**
   * QuickTime movie_7
   */
  FILE_NUM_397("6674797071742020", 4, "MOV"),
  /**
   * Radiance High Dynamic Range image file
   */
  FILE_NUM_93("233F52414449414E", 0, "HDR"),
  /**
   * RagTime document
   */
  FILE_NUM_158("43232B44A4434DA5", 0, "RTD"),
  /**
   * RealAudio file
   */
  FILE_NUM_105("2E524D4600000012", 0, "RA"),
  /**
   * RealAudio streaming media
   */
  FILE_NUM_106("2E7261FD00", 0, "RA"),
  /**
   * RealMedia metafile
   */
  FILE_NUM_413("727473703A2F2F", 0, "RAM"),
  /**
   * RealMedia streaming media
   */
  FILE_NUM_104("2E524D46", 0, "RM|RMVB"),
  /**
   * RealPlayer video file (V11+)
   */
  FILE_NUM_103("2E524543", 0, "IVR"),
  /**
   * RedHat Package Manager
   */
  FILE_NUM_502("EDABEEDB", 0, "RPM"),
  /**
   * Relocatable object code
   */
  FILE_NUM_433("80", 0, "OBJ"),
  /** Resource Interchange File Format */
  /** 4X Movie video */
  /** Micrografx Designer graphic */
  /** Corel Presentation Exchange metadata */
  /** Video CD MPEG movie */
  /** CorelDraw document */
  /**
   * Windows animated cursor
   */
  FILE_NUM_324("52494646", 0, "AVI|CDA|QCP|RMI|WAV|WEBP|4XM|DS4|CMX|DAT|CDR|ANI"),
  /**
   * Rich Text Format
   */
  FILE_NUM_428("7B5C72746631", 0, "RTF"),
  /**
   * RIFF CD audio
   */
  FILE_NUM_161("43444441666D7420", 8, "CDA"),
  /**
   * RIFF Qualcomm PureVoice
   */
  FILE_NUM_313("514C434D666D7420", 8, "QCP"),
  /**
   * RIFF WebP
   */
  FILE_NUM_355("57454250", 8, "WEBP"),
  /**
   * RIFF Windows Audio
   */
  FILE_NUM_147("415649204C495354", 8, "AVI"),
  /**
   * RIFF Windows Audio
   */
  FILE_NUM_354("57415645666D7420", 8, "WAV"),
  /**
   * RIFF Windows MIDI
   */
  FILE_NUM_325("524D494464617461", 8, "RMI"),
  /**
   * Runtime Software disk image
   */
  FILE_NUM_78("1A52545320434F4D", 0, "DAT"),
  /**
   * SAS Transport dataset
   */
  FILE_NUM_203("484541444552205245434F52442A2A2A", 0, "XPT"),
  /**
   * Shareaza (P2P) thumbnail
   */
  FILE_NUM_315("52415A4154444231", 0, "DAT"),
  /**
   * Shockwave Flash file
   */
  FILE_NUM_171("435753", 0, "SWF"),
  /**
   * Shockwave Flash player
   */
  FILE_NUM_197("465753", 0, "SWF"),
  /**
   * Show Partner graphics file
   */
  FILE_NUM_201("475832", 0, "GX2"),
  /**
   * Sietronics CPI XRD document
   */
  FILE_NUM_334("53494554524F4E49", 0, "CPI"),
  /**
   * Silicon Graphics RGB Bitmap
   */
  FILE_NUM_41("01DA01010003", 0, "RGB"),
  /**
   * SkinCrafter skin
   */
  FILE_NUM_52("07534B46", 0, "SKF"),
  /**
   * Skype audio compression
   */
  FILE_NUM_92("232153494C4B0A", 0, "SIL"),
  /**
   * Skype localization data file
   */
  FILE_NUM_235("4D4C5357", 0, "MLS"),
  /**
   * Skype user data file
   */
  FILE_NUM_401("6C33336C", 0, "DBB"),
  /**
   * SmartDraw Drawing file
   */
  FILE_NUM_337("534D415254445257", 0, "SDR"),
  /**
   * SMPTE DPX (big endian)
   */
  FILE_NUM_332("53445058", 0, "SDPX"),
  /**
   * SMPTE DPX file (little endian)
   */
  FILE_NUM_363("58504453", 0, "DPX"),
  /** SMS text (SIM) */
  // FILE_NUM_409("6F3C", 0, "(none)"),
  /**
   * Sonic Foundry Acid Music File
   */
  FILE_NUM_412("72696666", 0, "AC"),
  /**
   * Sony Compressed Voice File
   */
  FILE_NUM_245("4D535F564F494345", 0, "CDR|DVF|MSV"),
  /**
   * Speedtouch router firmware
   */
  FILE_NUM_151("424C49323233", 0, "BIN|BLI|RBI"),
  /** Sprint Music Store audio */
  // FILE_NUM_207("49443303000000", 0, "KOZ"),
  /**
   * SPSS Data file
   */
  FILE_NUM_96("24464C3240282329", 0, "SAV"),
  /**
   * SQL Data Base
   */
  FILE_NUM_39("010F0000", 0, "MDF"),
  /**
   * SQLite database file
   */
  FILE_NUM_340("53514C69746520666F726D6174203300", 0, "DB"),
  /**
   * Steganos virtual secure drive
   */
  FILE_NUM_136("414376", 0, "SLE"),
  /**
   * StorageCraft ShadownProtect backup file
   */
  FILE_NUM_338("5350464900", 0, "SPF"),
  /**
   * StuffIt archive
   */
  FILE_NUM_336("5349542100", 0, "SIT"),
  /**
   * StuffIt compressed archive
   */
  FILE_NUM_344("5374756666497420", 0, "SIT"),
  /**
   * SuperCalc worksheet
   */
  FILE_NUM_345("537570657243616C", 0, "CAL"),
  /**
   * Surfplan kite project file
   */
  FILE_NUM_121("3A56455253494F4E", 0, "SLE"),
  /**
   * Symantec Wise Installer log
   */
  FILE_NUM_101("2A2A2A2020496E73", 0, "LOG"),
  /**
   * Symantex Ghost image file
   */
  FILE_NUM_527("FEEF", 0, "GHO|GHS"),
  /** SZDD file format */
  // FILE_NUM_343("535A444488F02733", 0, "(none)"),
  /**
   * Tajima emboridery
   */
  FILE_NUM_225("4C413A", 0, "DST"),
  /**
   * Tape Archive
   */
  FILE_NUM_420("7573746172", 257, "TAR"),
  /**
   * TargetExpress target file
   */
  FILE_NUM_232("4D435720546563686E6F676F6C696573", 0, "MTE"),
  /** tcpdump (libpcap) capture file */
  // FILE_NUM_447("A1B2C3D4", 0, "(none)"),
  /** Tcpdump capture file */
  // FILE_NUM_117("34CDB2A1", 0, "(none)"),
  /**
   * The Bat! Message Base Index
   */
  FILE_NUM_38("01014719A400000000000000", 0, "TBI"),
  /**
   * Thumbs.db subheader
   */
  FILE_NUM_511("FDFFFFFF", 512, "DB"),
  /**
   * Thunderbird|Mozilla Mail Summary File
   */
  FILE_NUM_108("2F2F203C212D2D203C6D64623A6D6F726B3A7A", 0, "MSF"),
  /**
   * TIFF file_1
   */
  FILE_NUM_205("492049", 0, "TIF|TIFF"),
  /**
   * TIFF file_2
   */
  FILE_NUM_209("49492A00", 0, "TIF|TIFF"),
  /**
   * TIFF file_3
   */
  FILE_NUM_236("4D4D002A", 0, "TIF|TIFF"),
  /**
   * TIFF file_4
   */
  FILE_NUM_237("4D4D002B", 0, "TIF|TIFF"),
  /**
   * TomTom traffic data
   */
  FILE_NUM_269("4E41565452414646", 0, "DAT"),
  /**
   * TrueType font file
   */
  FILE_NUM_25("0001000000", 0, "TTF"),
  /**
   * UFA compressed archive
   */
  FILE_NUM_349("554641C6D2C1", 0, "UFA"),
  /**
   * UFO Capture map file
   */
  FILE_NUM_350("55464F4F72626974", 0, "DAT"),
  /**
   * Underground Audio
   */
  FILE_NUM_330("5343486C", 0, "AST"),
  /**
   * Unicode extensions
   */
  FILE_NUM_348("55434558", 0, "UCE"),
  /**
   * Unix archiver (ar)|MS Program Library Common Object File Format (COFF)
   */
  FILE_NUM_86("213C617263683E0A", 0, "LIB"),
  /**
   * User Interface Language
   */
  FILE_NUM_126("3C3F786D6C2076657273696F6E3D22", 0, "XML"),
  /** UTF-16|UCS-2 file */
  // FILE_NUM_528("FEFF", 0, "(NONE)"),
  /** UTF-32|UCS-2 file */
  // FILE_NUM_539("FFFE", 0, "(NONE)"),
  /** UTF-32|UCS-4 file */
  // FILE_NUM_540("FFFE0000", 0, "(NONE)"),
  /** UTF8 file */
  // FILE_NUM_503("EFBBBF", 0, "(none)"),
  /**
   * UUencoded BASE64 file
   */
  FILE_NUM_381("626567696E2D626173653634", 0, "b64"),
  /** UUencoded file */
  // FILE_NUM_380("626567696E", 0, "(none)"),
  /**
   * vCard
   */
  FILE_NUM_150("424547494E3A5643", 0, "VCF"),
  /**
   * VideoVCD|VCDImager file
   */
  FILE_NUM_183("454E545259564344", 0, "VCD"),
  /**
   * Virtual PC HD image
   */
  FILE_NUM_384("636F6E6563746978", 0, "VHD"),
  /**
   * Visio|DisplayWrite 4 text file
   */
  FILE_NUM_277("4F7B", 0, "DW4"),
  /**
   * Visual Basic User-defined Control file
   */
  FILE_NUM_352("56455253494F4E20", 0, "CTL"),
  /**
   * Visual C PreCompiled header
   */
  FILE_NUM_351("564350434830", 0, "PCH"),
  /**
   * Visual C++ Workbench Info File
   */
  FILE_NUM_368("5B4D535643", 0, "VCW"),
  /**
   * Visual Studio .NET file
   */
  FILE_NUM_266("4D6963726F736F66742056697375616C", 0, "SLN"),
  /**
   * Visual Studio Solution subheader
   */
  FILE_NUM_513("FDFFFFFF04", 512, "SUO"),
  /** VLC Player Skin file */
  /**
   * GZIP archive file
   */
  FILE_NUM_81("1F8B08", 0, "VLT|GZ"),
  /**
   * VMapSource GPS Waypoint Database
   */
  FILE_NUM_268("4D73526366", 0, "GDB"),
  /**
   * VMware 3 Virtual Disk
   */
  FILE_NUM_166("434F5744", 0, "VMDK"),
  /**
   * VMware 4 Virtual Disk description
   */
  FILE_NUM_89("23204469736B2044", 0, "VMDK"),
  /**
   * VMware 4 Virtual Disk
   */
  FILE_NUM_219("4B444D", 0, "VMDK"),
  /**
   * VMware BIOS state file
   */
  FILE_NUM_239("4D52564E", 0, "NVRAM"),
  /**
   * VocalTec VoIP media file
   */
  FILE_NUM_371("5B564D445D", 0, "VMD"),
  /**
   * Walkman MP3 file
   */
  FILE_NUM_356("574D4D50", 0, "DAT"),
  /**
   * WebM video file
   */
  FILE_NUM_76("1A45DFA3", 0, "WEBM"),
  /**
   * WhereIsIt Catalog
   */
  FILE_NUM_173("436174616C6F6720", 0, "CTF"),
  /**
   * Wii-GameCube
   */
  FILE_NUM_346("54485000", 0, "THP"),
  /**
   * Win Server 2003 printer spool file
   */
  FILE_NUM_400("68490000", 0, "SHD"),
  /**
   * Win2000|XP printer spool file
   */
  FILE_NUM_398("67490000", 0, "SHD"),
  /**
   * Win95 password file
   */
  FILE_NUM_454("B04D4643", 0, "PWL"),
  /**
   * Win98 password file
   */
  FILE_NUM_493("E3828596", 0, "PWL"),
  /**
   * Win9x printer spool file
   */
  FILE_NUM_221("4B490000", 0, "SHD"),
  /**
   * Win9x registry hive
   */
  FILE_NUM_169("43524547", 0, "DAT"),
  /**
   * WinAmp Playlist
   */
  FILE_NUM_374("5B706C61796C6973745D", 0, "PLS"),
  /**
   * Windows 7 thumbnail
   */
  FILE_NUM_163("434D4D4D15000000", 0, "DB"),
  /**
   * Windows 7 thumbnail_2
   */
  FILE_NUM_210("494D4D4D15000000", 0, "DB"),
  /**
   * Windows application log
   */
  FILE_NUM_426("7B0D0A6F20", 0, "LGC|LGD"),
  /**
   * Windows calendar
   */
  FILE_NUM_457("B5A2B0B3B3B0A5B5", 0, "CAL"),
  /** Windows cursor */
  /**
   * QuattroPro spreadsheet
   */
  FILE_NUM_15("00000200", 0, "CUR|WB2"),
  /**
   * Windows Disk Image
   */
  FILE_NUM_5("0000000014000000", 0, "TBI"),
  /**
   * Windows dump file
   */
  FILE_NUM_233("4D444D5093A7", 0, "DMP|HDMP"),
  /**
   * Windows Event Viewer file
   */
  FILE_NUM_110("300000004C664C65", 0, "EVT"),
  /**
   * Windows executable file_1
   */
  FILE_NUM_495("E8", 0, "COM|SYS"),
  /**
   * Windows executable file_2
   */
  FILE_NUM_496("E9", 0, "COM|SYS"),
  /**
   * Windows executable file_3
   */
  FILE_NUM_497("EB", 0, "COM|SYS"),
  /**
   * Windows executable
   */
  FILE_NUM_529("FF", 0, "SYS"),
  /**
   * Windows graphics metafile
   */
  FILE_NUM_488("D7CDC69A", 0, "WMF"),
  /**
   * Windows Help file_1
   */
  FILE_NUM_24("0000FFFFFFFF", 6, "HLP"),
  /**
   * Windows Help file_2
   */
  FILE_NUM_133("3F5F0300", 0, "GID|HLP"),
  /**
   * Windows help file_3
   */
  FILE_NUM_226("4C4E0200", 0, "GID|HLP"),
  /**
   * Windows icon|printer spool file
   */
  FILE_NUM_12("00000100", 0, "ICO|SPL"),
  /**
   * Windows international code page
   */
  FILE_NUM_532("FF464F4E54", 0, "CPI"),
  /**
   * Windows Media Audio|Video File
   */
  FILE_NUM_111("3026B2758E66CF11", 0, "ASF|WMA|WMV"),
  /**
   * Windows Media Player playlist
   */
  FILE_NUM_267("4D6963726F736F66742057696E646F", 84, "WPL"),
  /**
   * Windows memory dump
   */
  FILE_NUM_281("504147454455", 0, "DMP"),
  /**
   * Windows prefetch file
   */
  FILE_NUM_66("1100000053434341", 0, "PF"),
  /**
   * Windows prefetch
   */
  FILE_NUM_329("53434341", 4, "PF"),
  /**
   * Windows Program Manager group file
   */
  FILE_NUM_307("504D4343", 0, "GRP"),
  /**
   * Windows Registry file
   */
  FILE_NUM_538("FFFE", 0, "REG"),
  /**
   * Windows shortcut file
   */
  FILE_NUM_223("4C00000001140200", 0, "LNK"),
  /**
   * Windows Vista event log
   */
  FILE_NUM_189("456C6646696C6500", 0, "EVTX"),
  /**
   * Windows Visual Stylesheet
   */
  FILE_NUM_125("3C3F786D6C2076657273696F6E3D", 0, "MANIFEST"),
  /** Windows|DOS executable file */
  /** Windows virtual device drivers */
  /** Control panel application */
  /** ActiveX|OLE Custom Control */
  /** VisualBASIC application */
  /** Screen saver */
  /** Font file */
  /** OLE object library */
  /** Library cache file */
  /**
   * MS audio compression manager driver
   */
  FILE_NUM_251("4D5A", 0, "COM|DLL|DRV|EXE|PIF|QTS|QTX|SYS|VXD|386|CPL|OCX|VBX|SCR|FON|OLB|AX|ACM"),
  /** WinDump (winpcap) capture file */
  // FILE_NUM_487("D4C3B2A1", 0, "(none)"),
  /**
   * WinNT Netmon capture file
   */
  FILE_NUM_326("52545353", 0, "CAP"),
  /**
   * WinNT printer spool file
   */
  FILE_NUM_390("66490000", 0, "SHD"),
  /**
   * WinNT registry file
   */
  FILE_NUM_411("72656766", 0, "DAT"),
  /**
   * WinNT Registry|Registry Undo files
   */
  FILE_NUM_316("52454745444954", 0, "REG|SUD"),
  /**
   * WinPharoah capture file
   */
  FILE_NUM_75("1A350100", 0, "ETH"),
  /**
   * WinPharoah filter file
   */
  FILE_NUM_485("D20A0000", 0, "FTR"),
  /**
   * WinRAR compressed archive
   */
  FILE_NUM_327("526172211A0700", 0, "RAR"),
  /** WinZip compressed archive */
  // FILE_NUM_358("57696E5A6970", 29152, "ZIP"),
  /**
   * Word 2.0 file
   */
  FILE_NUM_489("DBA52D00", 0, "DOC"),
  /**
   * Word document subheader
   */
  FILE_NUM_501("ECA5C100", 512, "DOC"),
  /**
   * WordPerfect dictionary
   */
  FILE_NUM_159("434246494C45", 0, "CBD"),
  /**
   * WordPerfect text and graphics
   */
  FILE_NUM_534("FF575043", 0, "WP|WPD|WPG|WPP|WP5|WP6"),
  /**
   * WordPerfect text
   */
  FILE_NUM_437("81CDAB", 0, "WPF"),
  /**
   * WordStar for Windows file
   */
  FILE_NUM_357("575332303030", 0, "WS2"),
  /**
   * WordStar Version 5.0|6.0 document
   */
  FILE_NUM_79("1D7D", 0, "WS"),
  /**
   * Works for Windows spreadsheet
   */
  FILE_NUM_530("FF00020004040554", 0, "WKS"),
  /**
   * XPCOM libraries
   */
  FILE_NUM_362("5850434F4D0A5479", 0, "XPT"),
  /**
   * XZ archive
   */
  FILE_NUM_509("FD377A585A00", 0, "XZ"),
  /**
   * Yamaha Synthetic music Mobile Application Format
   */
  FILE_NUM_238("4D4D4D440000", 0, "MMF"),
  /** zisofs compressed file */
  // FILE_NUM_119("37E45396C9DBD607", 0, "(none)"),
  /**
   * ZLock Pro encrypted ZIP
   */
  FILE_NUM_300("504B030414000100", 0, "ZIP"),
  /**
   * ZoneAlam data file
   */
  FILE_NUM_264("4D5A90000300000004000000FFFF", 0, "ZAP"),
  /**
   * ZOO compressed archive
   */
  FILE_NUM_365("5A4F4F20", 0, "ZOO"),
  /**
   * ZoomBrowser Image Index
   */
  FILE_NUM_425("7A626578", 0, "INFO"),
  /**
   * ZSOFT Paintbrush file_1
   */
  FILE_NUM_56("0A020101", 0, "PCX"),
  /**
   * ZSOFT Paintbrush file_2
   */
  FILE_NUM_57("0A030101", 0, "PCX"),
  /**
   * ZSOFT Paintbrush file_3
   */
  FILE_NUM_58("0A050101", 0, "PCX"),
  /**
   * 兼容未找到合适的扩展名情况
   */
  NON("", 0, "");

  public static final int MAX_PATTERN_CHARACTER_LENGTH = 38;

  /**
   * FrontBlock -> Pattern -> Bytes
   */
  private String pattern;
  /**
   * FrontBlock -> Pattern -> Pos
   */
  private int position;
  /**
   * Info -> Ext
   */
  private String[] extensions;

  private FileSignatures(String pattern, int position, String extension) {
    this.pattern = pattern;
    this.position = position;

    boolean isMultiExtension = extension.contains("|");
    if (isMultiExtension) {
      this.extensions = extension.split("\\|");
    } else {
      this.extensions = new String[]{extension};
    }
  }

  String getPattern() {
    return pattern;
  }

  int getPosition() {
    return position;
  }

  public String[] getExtensions() {
    return extensions;
  }

  /**
   * 不是准确的扩展名.
   * <p>
   * 只通过文件头签名无法准确判断文件类型
   *
   * @return boolean
   */
  public boolean isDisputed() {
    return extensions.length > 1;
  }

  /**
   * 是否为扩展名X.
   *
   * @param ext X
   * @return 当前文件签名是否与参数相同
   */
  public boolean is(String ext) {
    if (Strings.isNullOrEmpty(ext)) {
      return false;
    }

    for (int i = 0; i < extensions.length; i++) {
      if (ext.equalsIgnoreCase(extensions[i])) {
        return true;
      }
    }
    return false;
  }

}

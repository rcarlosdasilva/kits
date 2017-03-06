package io.github.rcarlosdasilva.kits.net.ua;

import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class VersionFetcherFromMap extends PatternBasedVersionFetcher {

  private final Map<String, Version> versions;

  VersionFetcherFromMap(Pattern pattern, Map<String, Version> versions) {
    super(pattern);
    this.versions = Collections.unmodifiableMap(versions);
  }

  protected Version createVersion(Matcher matcher) {
    return versions.get(matcher.group(1));
  }

}

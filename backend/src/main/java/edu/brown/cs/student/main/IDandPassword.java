package edu.brown.cs.student.main;

import java.util.HashMap;

public class IDandPassword {
  HashMap<String, String> loginInformation = new HashMap<String, String>();

  public IDandPassword() {
    loginInformation.put("gcantera", "GuadalupePass!");
    loginInformation.put("jbenzon", "JoshPass1");
    loginInformation.put("jdoming8", "JillianPass2");
    loginInformation.put("smendir1", "ShivaniPass3");
    loginInformation.put("sgeorge8", "SarahPass4");
  }

  protected HashMap<String, String> getLoginInformation() {
    return loginInformation;
  }
}

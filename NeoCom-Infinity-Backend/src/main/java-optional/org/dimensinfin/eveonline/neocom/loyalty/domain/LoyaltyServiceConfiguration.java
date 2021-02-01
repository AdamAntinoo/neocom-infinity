package org.dimensinfin.eveonline.neocom.loyalty.domain;/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class LoyaltyServiceConfiguration {
private LoyaltyServiceConfiguration(){}
// - B U I L D E R
public static class Builder {
private final LoyaltyServiceConfiguration onConstruction;
public Builder (){
this.onConstruction = new LoyaltyServiceConfiguration();
}
public LoyaltyServiceConfiguration build(){
return this.onConstruction;
}
}
}
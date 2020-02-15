[ADD A MODULE TO THE SERIALIZER]
	@Bean
	public JodaModule jodaModule() {
		return new JodaModule();
	}

[SERIALIZE A RESPONSE TO BE MATCHED AGAINST A FILE]
ObjectMapper objectMapper = new ObjectMapper();
Car car = new Car("yellow", "renault");
objectMapper.writeValue(new File("target/car.json"), car);

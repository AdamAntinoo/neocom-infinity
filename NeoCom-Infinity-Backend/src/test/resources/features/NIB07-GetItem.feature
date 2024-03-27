@NIB07 @NeoItem
Feature: [NIB07] This is a new service endpoint separate from the NeoCom service that has the main role to return Eve Item data.

  Retrieve Eve Item data in a basic state or with added detailed data as market information or other details.

  @NIB07.01 @NeoItem
  Scenario: [NIB07.01] Get the base data for a Eve Online game item.
     Given a request to the "Get Item Basic" endpoint with the next data
      | itemId  |
      | 3400 |
    When the "Get Item Basic" request is processed
    Then the response status code is 200
    And the resulting item data has the next contents
      | jsonClass | typeId | name                 | groupId | groupName  | categoryId | categoryName | tech   | volume               | price          | isBlueprint | urlforItem                                   |
      | NeoItem   | 3400   | Outpost Construction | 268     | Production | 16         | Skill        | Tech I | 0.009999999776482582 | 1.2019065352E8 | false       | https://image.eveonline.com/Type/3400_64.png |

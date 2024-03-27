@NIN02 @Authentication @Core
Feature: [NIN02]-Do authentication validation and use it for ESI calls.

  [STORY] The endpoint should receive an authentication token.
  [STORY] That token should match the current request boundaries (character, corporation, etc) and scope.
  [STORY] That token sjould be usable for secured ESI calls.

  @NIN02.01
  Scenario: [NIN02.01]-Check that any endpoint request has a valid token.
    # Given a environment prepared for capsuleer 93813310
    When the endpoint 'capsuleer/miningoperations' is activated from request
    # Then there is a Token cookie
    # And the received token is valid

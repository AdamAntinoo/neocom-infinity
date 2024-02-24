@A01 @Assets
Feature: Read and process the Esi Assets into the Nest backend server. Add additional features like persistence and delta processing.

    - Go to the Esi Assets provider and get the list of assets for a character.
    - Persist the list of assets for a character with a timestamp to retrieve assets each five minutes.
    - Process  the list of assets received against the last repository copy to calculate deltas.

    @A01.01
    Scenario: Go to the Asset provider with the mock character Perico Tuerto and get the initial list of assets. The asset count should match.
        When retrieve "Assets/character" for character "93813310"
        Then the response status code is "200"
            And the number of assets should be "1000"

    # @A01.02
    # Scenario: Go to the Asset provider with the mock character Perico Tuerto and validate that a single Asset has the expected contents.
    #     When Call to "/"
    #     Then the response status code should be "200"
    #         And the response should be "Hello World!"

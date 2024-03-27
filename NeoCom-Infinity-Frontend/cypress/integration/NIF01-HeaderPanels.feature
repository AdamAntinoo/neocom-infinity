@NIF01 @HeaderSection
Feature: [NIF01]-The Header has a set of pre defined panels that should contain precise data.

This feature describes some panels common to a set of pages and mostly includes elements that have no interaction with the user.

    # @NIF01 @NIF01.01
    # Scenario: [NIF01.01]-There should be a single application data panel.
    #     Given one instance of AppInfoPanel
    #     Then there is a "app-info-panel" with the next fields
    #         | app-name        | app-version | app-copyright                      |
    #         | NEOCOM.INFINITY | 0.19.0 dev  | © 2019,2020 Dimensinfin Industries |
    #     And the border color of the "app-info-panel" is "WHITE"
    #     And the opacity interaction for the "app-info-panel" is "disabled"

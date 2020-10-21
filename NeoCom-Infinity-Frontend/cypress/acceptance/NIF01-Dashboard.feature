@NIF01
Feature: [STORY] Describe the structure and contents for the Dashboard page. This is the landing page for a new Login.

    The Dashboard describes the current loged Pilot and the other entities related to the dependency hierarchy.
    There are panels for Pilot public data, Corporation and Alliance.
    The story should check the asynchronous access to the Universe available data and the rendering contents.

    # Background: Application landing page
    #     Given the application NeoCom-Infinity-frontend
    #     Given a valid Credential on the session storage
    #     Then the page "Dashboard" is activated

    # - D A S H B O A R D
    # @NIF01.E.01
    # Scenario: [NIF01.E.01]-If there is no Credential on the local session storage then the Dashboard page should show an informational message.
    #     Given the application NeoCom-Infinity-frontend
    #     Given a valid Credential on the session storage
    #     # - Remove the Credential to force the exception.
    #     Given there is no Credential on session storage
    #     Then the page "Dashboard" is activated
    #     Then the page "Dashboard" has 1 panels
    #     # - Check the panel contents visible on the page
    #     Given the target is the panel of type "exception"
    #     Then field named "exceptionTitle" with label "TITLE" has contents "Rendering Pilot Public Data. No Credential Found."
    #     And field named "exceptionMessage" with label "MESSAGE" has contents "Unable to display Pilot data. There is no credential available to access."

    # - P I L O T
    @NIF01.01
    Scenario: [NIF01.01]-There is a Pilot panel for the logged Pilot.
        Given the Dashboard page activation
        # - Count the pilot panels that should be present on the page.
        Then the target has 1 "pilot-panel"
        Given the target the "pilot-panel" with id "92223647"
        Then the target has the title "Current Pilot"

    @NIF01.02
    Scenario: [NIF01.02]-Validate the current Pilot contents and visual rendering.
        Given the Dashboard page activation
        # - Check for the downloading pilot data message
        # And the loading panel shows "Clasificando Trabajos..."
        # When the loading panel completes
        # - Check the Pilot panel contents
        Given the target the "pilot-panel" with id "92223647"
        Then the target has 1 "pilot"
        Given the target the "pilot" with id "92223647"
        Then field named "pilotIdentifier" with label "PILOTID" has contents "[93813310]"
        And field named "pilotName" with label "PILOTNAME" has contents "Adam Antinoo"
        And field named "pilotAncestry" with label "PILOTANCESTRY" has contents "Minmatar - "
        And field named "pilotGender" with label "PILOTGENDER" has contents "Male"
        And field named "pilotWalletBalance" with label "PILOTWALLETBALANCE" has contents "654.987 ISK"
        And field named "pilotLastLocation" with label "PILOTLASTLOCATION" has contents "location"


# @D3D20.03
# Scenario: [D3D20.03]-Validate the contents for the Part Group.
#     # - Check the Part Group contents
#     Given the target the "part-container" with id "0972b78a-8eb7-4d53-8ada-b5ae3bfda0f2"
#     Then field named "label" with label "ETIQUETA" has contents "Boquilla Ganesha - Figura"
#     And field named "description" with label "DESCRIPCION" has contents "Boquilla para fomar en narguile. Compuesta de 3 piezas desmontables."
#     And field named "buildTime" with label "TIEMPO" has contents "90 min."
#     And field named "weight" with label "PLASTICO" has contents "8 gr."
#     And target has an actionable image named "edit-button"
#     And actionable image named "edit-button" is "enabled"
#     And target has an actionable image named "expand-button"

# @D3D20.04
# Scenario: [D3D20.04]-Validate a part when it is not being edited.
#     # - Expand a Part Group to see the Parts
#     Given the target the "part-container" with id "0972b78a-8eb7-4d53-8ada-b5ae3bfda0f2"
#     When the target item is expanded
#     # - Check the Part contents for an ACTIVE Part.
#     Given the target the "part" with id "6939c6cc-297f-48ca-8f17-25fa18c3dbc7"
#     Then field named "material" with label "MATERIAL/COLOR" has contents "PLA/ROSA"
#     And field named "stock" with label "STOCK" has contents "5"
#     And field named "stockAvailable" with label "DISPONIBLE" has contents "0"
#     And field named "cost" with label "COSTE" has contents "1 €"
#     And field named "price" with label "PRECIO" has contents "6 €"
#     And field named "active" with label "ACTIVA" has contents "ACTIVA"
#     And target has an actionable image named "edit-button"
#     And target has an actionable image named "duplicate-button"
#     And target has an actionable image named "save-disabled"
#     # - Check the Part contents for an CANCELED Part.
#     Given the target the "part" with id "4cf23190-d140-4681-93e5-2b2d02dfba39"
#     Then field named "material" with label "MATERIAL/COLOR" has contents "PLA/VERDE TRANSPARENTE"
#     And field named "stock" with label "STOCK" has contents "5"
#     And field named "stockAvailable" with label "DISPONIBLE" has contents "4"
#     And field named "cost" with label "COSTE" has contents "1 €"
#     And field named "price" with label "PRECIO" has contents "6 €"
#     And field named "active" with label "ACTIVA" has contents "FUERA PROD."
#     And target has an actionable image named "edit-button"
#     And target has an actionable image named "duplicate-button"
#     And target has an actionable image named "save-disabled"

# @D3D20.05
# Scenario: [D3D20.05]-Validate the different color tagging for the Model and Parts states.
#     Given the target the "model" with id "0f789845-cdc6-48ce-a0ce-cbaf63cffab5"
#     Then the target item has a "blueviolet" tag
#     # - Expand a Part Group to see the Parts
#     Given the target the "part-container" with id "0972b78a-8eb7-4d53-8ada-b5ae3bfda0f2"
#     When the target item is expanded
#     Given the target the "part" with id "6939c6cc-297f-48ca-8f17-25fa18c3dbc7"
#     Then the target item has a "greenyellow" tag
#     Given the target the "part" with id "4cf23190-d140-4681-93e5-2b2d02dfba39"
#     Then the target item has a "orangered" tag

# @D3D20.06
# Scenario: [D3D20.06]-Validate the input fields that should be displayed when the Edit Part is activated.
#     # - Activate the Part editing
#     Given the target the "part-container" with id "0972b78a-8eb7-4d53-8ada-b5ae3bfda0f2"
#     When the target item is expanded
#     Given the target the "part" with id "6939c6cc-297f-48ca-8f17-25fa18c3dbc7"
#     When target actionable image "edit-button" is clicked
#     # - Validate edit part form fields
#     Then the target item has a form field named "stock" with label "STOCK" and contents "5"
#     And the target item has a form field named "stockAvailable" with label "DISPONIBLE" and contents "0"
#     And the target item has a form field named "cost" with label "COSTE" and contents "1"
#     And the target item has a form field named "price" with label "PRECIO" and contents "6"
#     And the target item has a form field named "active" with label "ACTIVA" and contents "on"
#     And form field named "stock" is "valid"
#     And form field named "stockAvailable" is "valid"
#     And form field named "cost" is "valid"
#     And form field named "price" is "valid"
#     # - Check that the save button is enabled
#     Given the target the "part" with id "6939c6cc-297f-48ca-8f17-25fa18c3dbc7"
#     And actionable image named "save-button" is "enabled"

# @D3D20.07
# Scenario: [D3D20.07]-If any of the editable fields is invalidated then check that the save button is disabled.
#     # - Activate the Part editing
#     Given the target the "part-container" with id "0972b78a-8eb7-4d53-8ada-b5ae3bfda0f2"
#     When the target item is expanded
#     Given the target the "part" with id "6939c6cc-297f-48ca-8f17-25fa18c3dbc7"
#     When target actionable image "edit-button" is clicked
#     # - Invalidated a field
#     Given the target the "part" with id "6939c6cc-297f-48ca-8f17-25fa18c3dbc7"
#     # Then 1 is set on form field "stock"
#     Then form field "stock" is cleared
#     And form field named "stock" is "invalid"
#     And actionable image named "save-button" is "disabled"

# @D3D20.08
# Scenario: [D3D20.08]-Validate the contents of a Model. If the Model is clicked then the Model expands to show the contents.
#     Given the target the "model" with id "0f789845-cdc6-48ce-a0ce-cbaf63cffab5"
#     When the target is clicked
#     # - Check the model contents shown when expanded
#     Then the target has a panel labeled "COMPOSICION" named "part-composition"
#     Then the target has 3 "part-stack"
#     Given the target the "part-stack" with id "9fd4337d-6a4d-47b3-a7ac-a61bd51fad39"
#     Then column named "quantity" has contents "x 1"
#     And column named "label" has contents "PLATAFORMA SLOT 1/32 - Guarda Tornillos"
#     And column named "material" has contents "PLA/BLANCO"

# @D3D20.09
# Scenario: [D3D20.09]-Validate the contents of a Part.
#     Given the target the "part-container" with id "0972b78a-8eb7-4d53-8ada-b5ae3bfda0f2"
#     When the target item is expanded
#     # - Validate the fields of a Part inside a part group
#     Given the target the "part" with id "6939c6cc-297f-48ca-8f17-25fa18c3dbc7"
#     Then field named "material" with label "MATERIAL" has contents "PLA/ROSA"
#     # And field named "color" with label "COLOR" has contents "ROSA"
#     And field named "stock" with label "STOCK" has contents "5"
#     And field named "stockAvailable" with label "DISPONIBLE" has contents "0"
#     And field named "cost" with label "COSTE" has contents "1 €"
#     And field named "price" with label "PRECIO" has contents "6 €"
#     And field named "active" with label "ACTIVA" has contents "ACTIVA"

# @D3D20.10
# Scenario: [D3D20.10]-Active Parts show a green corner while inactive show it orange.
#     Given the target the "part-container" with id "0972b78a-8eb7-4d53-8ada-b5ae3bfda0f2"
#     Given the target item is expandable
#     When the target item is expanded
#     # - Count the Parts on the panel because they are not contained on the Part Container
#     Given the target is the panel of type "catalog"
#     Then the target has 6 "part"
#     # - Validate Part corners
#     And active "part" shows a green corner
#     And inactive "part" shows an orange corner

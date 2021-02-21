Feature: Amazon Product Search

  Scenario: To search nikon

    Given the user launches Amazon
    When the user is on Amazon homepage
    Then the user searches Nikon on Amazon search bar
    Then the user sorts High to Low from the sortby list
    Then the user selects the second item
    And verify the text matching with Nikon D3X

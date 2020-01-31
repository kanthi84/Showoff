Feature: As a user I am testing RAILS REACT APP for
  user creation
  updating user

  @test
  Scenario Outline: I am creating new user
#    Given I have created the access token
#    When I create user with "<firstName>" and "<lastName>" and "<password>" and "<email>"
#    Then User created successfully
#    When I upadate user with "<firstName>" and "<lastName>" and "<dob>"
#    Then user detail is updated
    When I fetch user details
    Then User details fetched successfully

    Examples:

    | firstName    | lastName   |  password    | email             |     dob
    | Soff4        | Test1      | password1    | soff.11@test.com  |    06081980
#    | Soff2        | Test2      | password2    | soff.22@test.com  |    05031970

  Scenario: I will update user
    When I upadte user details
    Then user detail is updated
















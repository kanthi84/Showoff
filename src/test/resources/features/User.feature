Feature: As a user I am testing RAILS REACT APP for
  user creation
  updating user
  fetching user details

  Background:
    Given I have created the access token

  @test
  Scenario Outline: I am creating new user, updating user and fetching the details by Id
    When I create user with "<firstName>" and "<lastName>" and "<password>" and "<email>"
    Then User created successfully
    When I upadate user with "<firstName>" and "<lastName>" and "<dob>"
    Then user detail is updated
    When I fetch user details by id
    Then User details fetched successfully


    Examples:

    | firstName    | lastName   |  password    | email             |     dob
    | Soff4        | Test1      | password1    | soff.41@test.com  |    06081980
    | Soff2        | Test2      | password2    | soff.42@test.com  |    05031970

  Scenario: I am fetching user details based on access token assigged and chage password
    When I fetch user details based on access token
    Then User details fetched successfully
    When I change the user password to "<newpassword>"
    Then Password successfully updated
















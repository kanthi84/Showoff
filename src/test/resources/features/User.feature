Feature: As a user I am testing RAILS REACT APP for
  user creation
  updating user
  fetching user details

  Background:
  #    This step is not functioning as API is not working
    Given I have created the access token

  @test
  #    This test creates two users as WE used data driven feature of BDD, it can be reduced to one or provided with more data to create more users.
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
    
  @test
  #    There is limitation to this test as user must know the existing password, there is no API ti fetch the user password
  Scenario: I am fetching user details based on access token assigged and chage password
    When I fetch user details based on access token
    Then User details fetched successfully
    When I change the user password to "<newpassword>"
    Then Password successfully updated
















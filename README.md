# anywr
Anywr : Java Spring Test

# goals:
Create a RESTful API using Java Spring Boot that implements a user authentication and authorization system. 
- Allow users to register by providing a unique username and password.
- Allow users to log in by providing their username and password.
- Allow users to retrieve their own profile information, such as their username and email address. (Optional if you have time)
- Use JSON Web Tokens (JWT) to authenticate and authorize API requests.
In addition, the API should include the following:
- Input validation to ensure that only valid data is entered into the database.
- Unit tests to ensure that the authentication and authorization system functions correctly. (Optional if you have time)

# implemented features

## _1. health check_
To check if service is up.<br>
This service is accessible without any authorisation.

/api/v1/user/health

## _2. registration and data validation_
<p>User can do registration with sending username, password and email as mandatory fields. The firsName and lastName are optinal (for more detail when getting profil).</p>
<p>There is some validations data applied when saving data in the database like email, notblank values and uniqueness of username.</p>

/api/v1/user/register</br>

{</br>
   "userName": "user1Login",</br>
   "password": "pass123",</br>
   "firstName": "user1",</br>
   "lastName": "user1",</br>
   "email": "user1@anywr.com"</br>
}

## _3. login/authentication_
User log in with providing his username and password.</br>
if user exist in database, a generated token with 'Login Successful' message returned. otherwise UNAUTHORIZED status with error message returned.

/api/v1/user/login</br>

{</br>
"userName":"user1Login",</br>
"password":"pass123"</br>
}

## _4. Check Profil/Authorisation_
After being logged user can access to his profil using 

/api/v1/user/userProfil</br>
with header = {'Authorization': 'Bearer + generated token'}

# Unit Test - SecurityTest

- testAuthentication
- testInvalidAuthentication
- testAuthorization
- testAuthorizationWithToken

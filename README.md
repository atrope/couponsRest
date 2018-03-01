# couponsRest
http://54.236.22.144:9000/
https://github.com/atrope/couponsRest

The code is made so that all 3 "modules" could be deployed as a standalone app by itself

# REST api
app/backend and app/rest
Admin - app/controllers/AdminController and app/views/admin and public folder (for css and js)
Home  - app/controllers/HomeControlles and app/views and public folder (for css and js)


# HOME
Here we make a GET request to our REST API to get all coupons and show them.
Coupon Page - The details of the coupon and the actual coupon code of it. Data is fetched through the GET to the specific coupon endpoint


# Admin
GET request to REST API to get all coupons and show them(same GET from Home but another view).
Edit - Following standards we make a PUT request to our API to change coupon ID X
Delete - Following standards we make a DELETE request to our API to DELETE coupon ID X
Add Coupon - Following standards we make a POST request to our API to CREATE coupon.


# API Endpoints
GET     /coupons -- Get all coupons
GET     /coupons/:id -- Get specific coupon ID X
DELETE  /coupons/:id -- Delete Coupon
PUT     /coupons/:id -- Edit Coupon
POST    /coupons - Create new Coupon

returns 200 or 4XX in error. Documentation for API not provided



# BONUS

#1 (5pts Bonus)
Admin Interface with the specified routes developed.

#4 (5pts Bonus)
REST api developed. explained above

#5 (5pts Bonus)
Deployed to Amazon Web Services, setup with Scala+Play+Mongo

#6 (5pts Bonus MAX)
Submited more than 5 days before the deadline

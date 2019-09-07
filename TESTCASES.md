## Manual test cases for scenario 3

#### Test 1 : Verify user able to access the raisin partner page.
* Given User has valid partner bank url: `https://www.raisin.com/bank`
* And Use choose `Other` from the list of regions pop up when it asked
* When User open the link
* Then User can see in the page header: Our offers, Banks, About, Become a Partner, Blog
* And User can see : My Account, Register button on the right top.

#### Test 2 : Validate the sorting functionality.
* Given User open partner bank page: `https://www.raisin.com/bank`
* And Use choose `Other` from the list of regions pop up when it asked
* And Default sorting order is by `Bank (A-Z)` on the page
* When User select sorting order by `Country (A-Z)`
* Then Page should sorted as per country name alphabetical ascending order. 
* When User select sorting order by `Country (Z-A)`
* Then Page should sorted as per country name alphabetical descending order. 

#### Test 3 : Find highest S&P country rating.
* Given User open partner bank page: `https://www.raisin.com/bank`
* And Use choose `Other` from the list of regions pop up when it asked
* When User select sorting order by `Country (A-Z)`
* And User search for highest S&P country rating
* Then User should find highest S&P country rating : `AA+`

#### Test 4 : Click invert now and validate registration page.
* Given User open partner bank page: `https://www.raisin.com/bank`
* And Use choose `Other` from the list of regions pop up when it asked
* When User select sorting order by `Country (A-Z)`
* And User click on first bank name on the list `alior-bank`
* Then User should see the bank details page
* And User should see the invest button
* When User click on invest now 
* Then User should see the registration page

#### Test 5 : Validate partner bank page.
* Given User open partner bank url: `https://www.raisin.com/bank`
* And Use choose `Other` from the list of regions pop up when it asked
* When User click on first partner bank from the list `alior-bank`
* Then User should see best deposit offer of `alior-bank`
* And User should see `invest now` button on right middle
* When User scroll down on the page
* Then User should see about `alior-bank`, offer details ,security and deposit guarantee etc
* Then User can subscribe newsletter by entering email address

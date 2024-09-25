# Test scenarios for gad application

## Scenario 1: User registers a new account
- Description : User might register a new account in gad application
- Prerequisites: User is not yet registered to the application

### Test Cases:

#### Test Case 1: Registration and then logging in with proper data

**Steps:**

1. Hover over "person" icon in the upper right corner and click on "Register" button.
2. Enter data (first name, last name, email, birthdate, password).
3. Select image from dropdown menu.
4. Click Register button.
5. Enter login data and press LogIn button.

**Entry data:**
- first name: John
- last name: Smith
- email: john.smith@mail.com
- birthdate: 1960-12-12
- password: pass

**Login data**
- email: john.smith@mail.com
- password: pass

**Expected results**

1. Registration page is displayed.
2. Data properly entered and visible.
3. Image correctly changed.
4. "User created" popup is displayed and user is redirected to login page.
5. User is logged in and redirected to "My account page".

#### Test Case 2: Registration with existing user email

**Steps:**
1. Hover over "person" icon in the upper right corner and click on "Register" button.
2. Enter data (first name, last name, email, birthdate, password).
3. Select image from dropdown menu.
4. Click Register button.
5. Repeat step 1,2,3 entering user2 data.

**Entry data:**

***User1***
- first name: John
- last name: Smith
- email: john.smith@mail.com
- birthdate: 1960-12-12
- password: pass

***User2***
- first name: John
- last name: Smithy
- email: john.smith@mail.com
- birthdate: 1970-12-12
- password: pass

**Expected result**
1. Registration page is displayed.
2. Data properly entered and visible.
3. Image correctly changed.
4. "User created" popup is displayed and user is redirected to login page.
5. "User not created! Email not unique" popup is displayed.

#### Test Case 3: Registration with only user email field entered

**Steps:**
1. Hover over "person" icon in the upper right corner and click on "Register" button.
2. Enter data (only email)
4. Click Register button.

**Entry data:**

- email: jan.nowak@mail.com

**Expected results**
1. Registration page is displayed.
2. Data properly entered and visible.
3. "This field is required" message is displayed below first name, last name and password fields.

#### Test Case 4: Registration with wrong format data (first name, last name, email and date fields)

**Steps:**
1. Hover over "person" icon in the upper right corner and click on "Register" button.
2. Enter data.
4. Click Register button.

**Entry data:**

- first name: John1
- last name: Smith2
- email: john.smith@mail
- birthdate: 12-12-1969
- password: pass

**Expected results**
1. Registration page is displayed.
2. Data properly entered and visible.
3. Error messages below inputs of first name, last name - "Please enter only Letters!",
   email - "Please provide a valid email address" and date - "Date must be in format YYYY-MM-DD" fields.

## Scenario 2: Adding an article by registered and logged in user
- Description : Registered and logged in user might add new articles.
- Prerequisites: User is registered and logged in


### Test Cases:

#### Test Case 1: Adding an article with proper data

**Prerequisites**
- Registration and logging in to application (i.e steps 1-4 from scenario1 test case1)
- Starting with "My account" page

**Steps:**

1. Click "Articles" button in the upper left corner
2. Click "Add Article" button on top of the bar.
3. Enter title, body data and select image.
4. Click Save button.

**Entry data:**
- Title: Will you lose your job because of AI?
- Body: Nobody cares

**Expected results**

1. Articles page is displayed. Add article button visible.
2. "Add New Entry page" visible with forms to add new article.
3. Data properly entered and new picture visible.
4. "Article was created" message is displayed. User redirected to single article page view that was already created. Number of articles increased by 1.(Check using API or DB)

#### Test Case 2: Adding an article with missing data

**Prerequisites**
- Registration and logging in to application (i.e steps 1-4 from scenario1 test case1)
- Starting with "My account" page

**Steps:**

1. Click "Articles" button in the upper left corner
2. Click "Add Article" button on top of the bar.
3. Enter only title field
4. Click Save button.
5. Delete recently entered title data and enter data to Body field.
6. Click Save button.

**Entry data:**
- Title: Another article
- Body: Lorem ipsum

**Expected results**

1. Articles page is displayed. Add article button visible.
2. "Add New Entry page" visible with forms to add new article.
3. Data properly entered.
4. "Article was not created" popup is displayed and user is not redirected to any other page. Number of written articles is not increased (check using API or in DB).
5. Data properly entered.
6. "Article was not created" popup is displayed and user is not redirected to any other page. Number of written articles is not increased (check using API or in DB).

#### Test Case 3: Adding an article by not logged in user

**Prerequisites**
- User not logged in
- Test starts on main page (localhost:3000)

**Steps:**

1. Click "Let's start!" button in the center part of page.

**Expected results**

1. User automatically redirected on Articles page. "Add Article" button not visible making it impossible to add new article without logging in.

#### Test Case 4: Adding an article with Title longer than 128 characters

**Prerequisites**
- Registration and logging in to application (i.e steps 1-4 from scenario1 test case1)
- Starting with "My account" page

**Steps:**

1. Click "Articles" button in the upper left corner
2. Click "Add Article" button on top of the bar.
3. Enter title, body data and select image.
4. Click Save button.

**Entry data:**
- Title: Przykładowy tekst, który ma dokładnie tyle znaków. Jest on starannie dobrany, aby spełnić moje wymagania dotyczące długości treści.
- Body: Lorem ipsum lorem

**Expected results**

1. Articles page is displayed. Add article button visible.
2. "Add New Entry page" visible with forms to add new article.
3. Data properly entered.
4. "Article was not created" popup is displayed and user is not redirected to any other page. Number of written articles is not increased (check using API or in DB). 422 error code expected and returned message should include text "Field validation: \"title\" longer than \"128\".

#### Test Case 5: Adding an article with Polish and special characters

**Prerequisites**
- Registration and logging in to application (i.e steps 1-4 from scenario1 test case1)
- Starting with "My account" page

**Steps:**

1. Click "Articles" button in the upper left corner
2. Click "Add Article" button on top of the bar.
3. Enter title, body data and select image.
4. Click Save button.

**Entry data:**
- Title: Odkryj świat: Żółw wśród@ raf koralowych – #wyjątkowa podróż pełna emocji i % niespodzianek!
- Body: Podróżując # wśród@ malowniczych! @ra$f ()&%koralowych, żółw majestatycznie unosi się w krystalicznie czystej wodzie. Odkryj piękno oceanu, którego tajemnice skrywają niezwykłe stworzenia. W tej podróży zobaczysz bogactwo barw, odcieni oraz różnorodność fauny, jakiej nie znajdziesz nigdzie indziej. Zbliż się do przyrody i zanurz w świat pełen emocji – od fascynacji po zachwyt. Niech ta przygoda pozostawi w Tobie niezapomniane wspomnienia.

**Expected results**

1. Articles page is displayed. Add article button visible.
2. "Add New Entry page" visible with forms to add new article.
3. Data properly entered.
4. "Article was created" message is displayed. User redirected to single article page view that was already created. Number of articles increased by 1.(Check using API or DB)

## Scenario 3: Adding a flashposts
- Description : Registered and logged in user might add flashposts at maximum 128 length
- Prerequisites: User is registered and logged in

#### Test Case 1: Adding a flashpost with correct length by logged in user

**Prerequisites**
- Registration and logging in to application (i.e steps 1-4 from scenario1 test case1)
- Starting with "My account" page

**Steps:**

1. Click "Flashposts" button in the upper left corner
2. Scroll to the upper part of the page and click "Create flashpost" button.
3. Enter flashpost data. Choose black background color (lower right corner).
4. Click "Create" button.

**Entry data:**
- flashpost: It is never too late to start IT!

**Expected results**

1. Flashposts page is displayed. "Flashposts button color changed to "grey".
2. Modal with title "Create Flashpost" opened.
3. Data properly entered. and tile with color changed to black.
4. "Flashpost created successfully" message displayed in upper right corner of the page. Newly added flashpost added on top of the flashposts list with user first name and last name

#### Test Case 2: Adding a flashpost with too long message by logged in user

**Prerequisites**
- Registration and logging in to application (i.e steps 1-4 from scenario1 test case1)
- Starting with "My account" page

**Steps:**

1. Click "Flashposts" button in the upper left corner
2. Scroll to the upper part of the page and click "Create flashpost" button.
3. Enter flashpost data. Choose black background color (lower right corner).
4. Click "Create" button.

**Entry data:**
- flashpost: Automatyzuj testy z Playwright i Git! Zwiększ efektywność, wdrażaj szybciej i poprawiaj jakość kodu. Przyszłość IT w twoich rękach!

**Expected results**

1. Flashposts page is displayed. "Flashposts button color changed to "grey".
2. Modal with title "Create Flashpost" opened.
3. After reaching 128 characters GUI is not allowing to enter more data. "O characters left visible in lower right corner of text area". *sending API POST request should result in 422 error code with message: "One of field is invalid (empty, invalid or too long) or there are some additional fields: Field validation: \"body\" longer than \"128\""

#### Test Case 3: Adding a flashpost by not logged in user

**Prerequisites**
- User not logged in
- Test starts on main page (localhost:3000)

**Steps:**

1. Click "Let's start!" button in the center part of page.
2. Click Flashposts button
3. Click "Create flashpost" button.
4. Click "Create" button.
5. Enter flashpost data. Choose blue background color (lower left corner).
6. Click "Create" button.
7. Click "Cancel" button

**Entry data:**
- flashpost: It is a trap!

**Expected results**

1. User automatically redirected on Articles page.
2. Flashposts page is displayed. "Flashposts" button color changed to "grey".
3. Modal with title "Create Flashpost" opened.
4. "Flashpost can't be empty" alert message displayed in the background.
5. Data properly entered and tile with color changed to blue.
6. "You can't create this flashpost." alert displayed on the upper right corner.
7. Modal closed and list of flashposts still visible.

#### Test Case 4: Adding a public flashpost by logged in user and verification if it is visible for non-logged in users

**Prerequisites**
- Registration and logging in to application (i.e steps 1-4 from scenario1 test case1)
- Starting with "My account" page

**Steps:**

1. Click "Flashposts" button in the upper left corner
2. Scroll to the upper part of the page and click "Create flashpost" button.
3. Enter flashpost data. Click checkbox to make flashpost public.
4. Click "Create" button.
5. Hover over user icon and Click "Logout button".
6. Click "Flashposts" button in the upper left corner.

**Entry data:**
- flashpost: Am I public????

**Expected results**

1. Flashposts page is displayed. "Flashposts button color changed to "grey".
2. Modal with title "Create Flashpost" opened.
3. Data properly entered and checkbox checked.
4. "Flashpost created successfully" message on the upper right corner. Flashpost visible on top of the list.
5. User redirected to login page.
6. Flashposts page is displayed. Flashpost with text "Am I public????" visible on top of flashposts.

#### Test Case 5: Adding a non-public flashpost by logged in user and verification if it is not visible for non-logged in users

**Prerequisites**
- Registration and/or logging in to application (i.e steps 1-4 from scenario1 test case1)
- Starting with "My account" page

**Steps:**

1. Click "Flashposts" button in the upper left corner
2. Scroll to the upper part of the page and click "Create flashpost" button.
3. Enter flashpost data. DO NOT click checkbox to make flashpost public.
4. Click "Create" button.
5. Hover over user icon and Click "Logout button".
6. Click "Flashposts" button in the upper left corner.

**Entry data:**
- flashpost: Am I non-public????

**Expected results**

1. Flashposts page is displayed. "Flashposts button color changed to "grey".
2. Modal with title "Create Flashpost" opened.
3. Data properly entered.
4. "Flashpost created successfully" message on the upper right corner. Flashpost visible on top of the list.
5. User redirected to login page.
6. Flashposts page is displayed. Flashpost with text "Am I non-public????" not visible on the list of flashposts.
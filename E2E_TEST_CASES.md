# End to end

### Test Cases:

#### E2EGad1Test: User registration, adding an article, comment and flashpost.

**Steps:**
1. Hover over "person" icon in the upper right corner and click on "Register" button.
2. Enter data (first name, last name, email, birthdate, password).
3. Select image from dropdown menu.
4. Click Register button.
5. Enter login data and press LogIn button.
6. Click "Articles" button in the upper left corner
7. Click "Add Article" button on top of the bar.
8. Enter title, body data and select image.
9. Click Save button.
10. Click "Articles" button again and in search box fill the title entered in step 8. Click GO button.
11. Click "See More", then "Add Comment" button and enter comment data.
12. Click "Comments" button on left side of navigation bar. Recently added comment should be in the upper left corner. Click "See comment".
13. Click "Flashposts" button and click "Create flashpost" button on upper part of the page.
14. Enter flashpost data, click public checkbox and set color to black.
15. Click "Logout" button after hovering over user icon.
16. Click "Articles" page and check if recently added article is in the left upper corner (verify user, Title)
17. Click Flashposts Button and check if recently added public flashpost is visible on top of the page.

**Entry data:**
- first name: John
- last name: Smith
- email: john.smith@mail.com
- birthdate: 1960-12-12
- password: pass

**Login data**
- email: john.smith@mail.com
- password: pass
- Title: Will you lose your job because of AI?
- Body: Nobody cares
- Comment: "There will always be some specialist needed."
- Flashpost: Hello everyone!

**Expected results**

1. Registration page is displayed.
2. Data properly entered and visible.
3. Image correctly changed.
4. "User created" popup is displayed and user is redirected to login page.
5. User is logged in and redirected to "My account page".
6. Articles page is displayed. Add article button visible.
7. "Add New Entry page" visible with forms to add new article.
8. Data properly entered and new picture visible.
9. "Article was created" message is displayed.
10. Only single recently added article displayed.
11. "Comment was created" popup displayed. Newly added comment visible on the bottom of the page. Comment text visible.
12. Recently added comment displayed. Added text the same as in entered in step 11.
13. Modal "Create flashpost" opened.
14. "Flashpost created successfully" popup displayed and flashpost visible on top of the screen
15. User successfully redirected to login page.
16. Article is visible, and Title and user are the same as previously added.
17. Flashpost is visible and text is correct.
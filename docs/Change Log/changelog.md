# Change Log

## Sunday 31st October + Monday 1st November

### Additions
- Finished off tests for BlogPostService
- Added tests and conversion logic for LocalDateTime to a pretty string for post to display
- Added About page with skills, interests and link to GitHub account
- Added IntelliJ Config to project files so I do not lose it again
- Updated Admin page with direct links to create and edit posts
### Modifications
- Changed PersistedBlogPost date field to the better LocalDateTime type
- Renamed the project artifact ID from mvp to app
- Renamed EditPostResponse to EditPostRequest (it is a request from the client)
  - Also removed Category and Date from this as they are not changeable (yet)
- Renamed NewPostResponse to NewPostRequest (same reason as above)
### Fixes
- Fixed ordering of posts - added a stream that sorts the posts by date then maps to ExistingPostRequest
- Fixed search box look on Safari (safari displays type="search" weird)
- Fixed failing Web layer tests
  - Implemented mock auth user in NewPostControllerTest
  - checked for redirect when non-authenticated user attempts to visit

## Tuesday 2nd - Friday 5th November
### Additions
- Added error message to admin login
- Added the About page with some content
- Admin page now has a 'new post' button
- Admin page now shows a lists of posts with an edit button to go directly to editing
### Modifications
- -Modified CSS on admin page
- ### Fixes
- Moved admin credentials to an ENV file.
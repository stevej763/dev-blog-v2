This week was full of nice little changes and helpful content updates to the site. I have increased my test coverage, fixed some bugs, increased security and added content. A bit of everything!

## Testing

My first goal this week was to have some more tests written for my business logic. The main area that needed testing was a class I call the `BlogPostService`. This is the domain layer between my controllers (Web/Presentation Layer) and the Repository (Persistence/Data Layer). I am not going to pretend I am following a certain architecture.I do not have strong enough understanding of what different architectures mean exactly to be very confident in this area yet. But after having read Martin Fowler's post on layering applications [here](https://martinfowler.com/bliki/PresentationDomainDataLayering.html). I am starting to understand and appreciate the nuances a lot better.

At this point I now feel confident in my tests for this class, it is where just about all logic in my app lives and was the top priority. I have written a reasonably sized test suite that covers everything from adding a new post, finding a single or multiple blog post and updating an existing blog post, to requesting to convert a persisted object to a domain object or to a web response object.

Most tests are fairly simple, such as:

```
    @Test
    public void  getLatestPostsShouldReturnAListOfExistingPostsSortedByDateDescending() {
        List<ExistingBlogPostResponse> expectedResponse = List.of(
                new ExistingBlogPostResponse("3", "title1", "description", "", "first post", "2021", PostCategory.BUILD_LOG),
                new ExistingBlogPostResponse("2", "title1", "description", "", "middle post", "2020", PostCategory.BUILD_LOG),
                new ExistingBlogPostResponse("1", "title1", "description", "", "last post", "2019", PostCategory.BUILD_LOG)
        );
        when(blogPostRepository.findAll()).thenReturn(persistedBlogPosts);
        
        List<ExistingBlogPostResponse> result = underTest.getLatestPosts();
        
        assertThat(result, is(expectedResponse));
    }
```
This checks that my sorting logic works correctly and that the service converts the `PersistedBlogPost` objects into a `ExistingBlogPostResponse` object. The main difference being that the persisted object holds a LocalDateTime field while the Response object contains a `prettyDate` string that formats it nicely for the frontend.

However, not all tests where so easy and have had to leave this one for now until I learn a better way to handle it:

```
    @Test
    public void shouldRequestRepositoryToAddNewPostToDatabase() {
        when(blogPostRepository.insert(any(PersistedBlogPost.class))).thenReturn(persistedBlogPost);

        underTest.addPost(newPostRequest);

        verify(blogPostRepository).insert(any(PersistedBlogPost.class));
    }
```

I wrote a comment above this test explaining my frustrations with it. Basically, my PersistedBlogPost object is created by a static builder object. In this process an ID is assigned to it (The value that `equals` checks for a matching instance). How can I test that the repository receives the exact instance of `PersistedBlogPost` and that I am returned a persisted object from the database that matches, if I do not know the ID that was generated. I could change the builder to an instance and stub the builder method, then I can return a mocked response. But that would mean changing another class just to get a unit test to pass which does not sound like the right way to do it.

For now, I am going to leave it how it is. My application is small enough that through manual testing I can easily see it is doing what it is supposed to be doing, and I would rather spend the limited time I have to work on this in other areas, it is after all a personal project, not an enterprise application.

## Content

There were a few content updates to the site over the past week. The first big addition was the [About](http://stevedevblog.com/about) page. It is not anything fancy just yet, just some text and a link to my [GitHub](https://github.com/stevej763) account. I also moved the welcome paragraph over to this page as I felt it made more sense to leave the Homepage to the main blog post content and what I made the site for in the About page.

The other major content addition is behind the scenes. I added a bit more to the Admin page to help me manage my posts.

![admin-page](https://stevejonesphotos.co.uk/wp-content/uploads/2021/11/admin-page.png)

There is now a nice big button for me to press to add a new post and a nicely formatted table that displays a list of all the posts and a button to go in and edit content if I wish. I was thinking of adding a Delete button too, but it is not really a top priority right now, with this being only my second post.

## Small changes

There were a few things I noticed after getting the site up and running that I wanted to get sorted as they would be a quick an easy fix, well easy, not so much quick. The first was to display the date field nicely on each blog post card. After a bit of googling I found I had used the old Java `Date` API for my date field in the persisted posts when I should have been using the newer `LocalDateTime` object. Switching over to the new version would allow me to use the `DateTimeFormatter` to easily make a pretty date object to show on the frontend. Thanks to IntelliJ renaming and changing a datatype is not too much hassle so that was quite straight forward. 

However, now I was wanting my date as a String object I needed a way to store it. Rather than adding a new field to the Persisted object, I thought it would be better to introduce a new `ExistingBlogPostResponse` that holds a string instead of a date. This conversion is done by a builder class. At some point in the future I could also move the Markdown parser into this builder so that the Response object holds everything it needs to display a post on the frontend. but I will leave that alone for the time being. For now, I can now display my dates in a pretty format. Which was the main goal.

Another small change was in the security of my app. I have now moved the admin credentials into an environment variable. This makes them easy to update and also means they are not stored in a public repo... At some point it would be good to be able to store and update the credentials in the database, but since it is just me working on this, an Environment variable will do.

## What's next?

In the coming week it would be good to get the `Blog` link on the navbar working, right now it just leads to a dead end. I have been thinking about how I would display the posts on the main blog page. To start with, it will probably just be a big list in date order, then maybe add some filters by Category and a sort-by option.

Speaking of categories, that is the other thing I want to modify. Right now I just have an ENUM with the blog categories hard-coded. I think it would be better to store these in the database and give the admin page the option to add/delete them. This would be more customisable and add a neat bit of functionality to the app.

Finally, an extension project if I get both the above done. Would be to add the ability for the admin page to select 3 posts to be featured on the front page. But it may have to wait until the next week.
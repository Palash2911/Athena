# Athena

### Problem Statement:
Every writer's first challenge is coming up with an idea for a story and getting it off the ground. The first part is crucial because it is what captures the audience's attention. As a result, working on the first section becomes challenging, and many writers may become demotivated if a good start is not made. Additionally, writers frequently run out of ideas mid-story and are unable to come up with fresh ones. Because of this, many writers simply begin a new story rather than finish an excellent story.

### Proposed Solution:
A short story writing app called Athena gives users a head start by generating a story between 100 and 200 words long based on their preferences. The user only needs to pick a genre for their narrative and create a brief prompt before they are ready to commence. This short story provides writers with clear instructions, which makes their work easier. Both authors in need of new inspiration and those who are stuck in the middle of their story might benefit from using this app.



### Application Images:  
                                                                              
<img src="Images/1_Login.png" width="240" height="450"><img src="Images/2_Signup.png" width="240" height="450"><img src="Images/3_Profile.png" width="240" height="450"><img src="Images/4_Category.png" width="240" height="450"><img src="Images/5_Prompt.png" width="240" height="450"><img src="Images/6_Story.png" width="240" height="450">


### Functionality & Concepts used:
The app has a simply elegant and interactive interface that assists the user in obtaining the idea for the desired story. Following are some of the Jetpack Compose concepts used to acquire the functionalities in the app : 

1. **Navigation**: This allows users to navigate across, into, and back out from the different pieces of content within the app. We have created multiple routes for navigation to travel through Athena. 
2. **Lazy Columns**: This is used to create a vertically scrolling list of the various choices to be offered as preferences. This allows the user to scroll the list and select the required choice accordingly. 
3. **Firebase Database**: We are also using firebase to update the status of the stories saved by the user, etc. The users can customize saved stories, and login into the application using the same.
4. **Use of States and the Mutable States**: Any changes to value will schedule the recomposition of any composable functions that read value.
5. **Interactive UI**: We have used various textfields, buttons, and cards for the user to interact with the application. For eg: The user can interact with the story generated, edit it as per their choice, and save the same. Or they can log in to their account to access their stories.

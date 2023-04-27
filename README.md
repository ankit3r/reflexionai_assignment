# reflexionai_assignment
This assignment given by Reflexion.Ai


This is an Android application created using Kotlin which has three fragments. 
The first fragment displays data from an API using Retrofit and stores it in the RoomDB. 
On scrolling at the end of the list, the second API is called and its data is displayed.
Each card will have a delete and favorite button. The second fragment will show only your favorite data cards.
The third fragment will have a static user profile.




## Installation and Cloneing

### To install the application, follow these steps:

* Clone the repository to your local machine.
* Open the project in Android Studio.
* Build and run the application on an Android device or emulator.


## Usage 
* Upon opening the app, you will see a list of  movies.
* Clicking on the favorite button will add the card to the favorites list.
* Clicking on the delete button will remove the card from the list.
* Click on a movie to view its details, including a summary, rating, release date, and cast.
* The second fragment will show only the favorite cards. Clicking on the delete and favorite button will remove the card from the favorites list.

## API Key
the api provided by Reflexion.Ai

## Libraries Used

* Retrofit - For making API requests and handling responses.
* Gson - For converting JSON responses to Kotlin objects.
* Glide - For loading and caching images.
* Coroutines - For handling asynchronous operations.
* ViewModel - For managing UI-related data.
* LiveData - For observing changes in data.
* RecyclerView - For displaying lists of data.
* Room database - to store data

## Screenshots
Here are some screenshots of the application:



| ![Screenshot_20230427_162340](https://user-images.githubusercontent.com/64691445/234842160-10cdff40-ea54-4bab-a6d2-199c08f4075a.jpg) | ![Screenshot_20230427_162345](https://user-images.githubusercontent.com/64691445/234842167-ea144aeb-14b7-4af2-88b6-7551bfd1da5d.jpg)    |    |
| :---:   | :---: | :---: |
| ![Screenshot_20230427_162349](https://user-images.githubusercontent.com/64691445/234842169-612bb90f-90d9-412d-8280-66aa18b5b5df.jpg) |![Screenshot_20230427_162355](https://user-images.githubusercontent.com/64691445/234842172-5a5c9ec3-607a-42a4-a0c8-4c9d59413ed4.jpg)















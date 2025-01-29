# Description
Simple app using the Art Institute of Chicago api to display a list of Artworks, with a details page clickthrough. https://api.artic.edu/docs/#quick-start

# Tech Used
Kotlin / Coroutines / StateFlow / Compose

retrofit / okhttp for network

Moshi for json parsing

Coil for async image loading

Hilt for dependency injection

Junit5 / Mockk for unit tests

# Architecture
Clean architecture approach, utilising 3 layers to keep a clear seperation of concerns, easier testability and maintainability. 

Data layer containing repository implementation, api calls, api models. 
Domain layer containing the domain models which the ui depends on, and interfaces for the data layer to depend on. 
UI/presentation layer containing the ui elements, viewmodels and navigation. 

Within this structure we have unidirectional data flow. States flow from our data layer, through the domain layer to the ui layer where it is held in our viewmodels. 
Events are driven by the UI and flow up through the domain layer to the data layer. 

# Next steps given more time
- Add more robust error handling, currently using a generic message in case anything goes wrong with the api calls / state retreival. A retry option on failure would be a quick win. 
- Finish test coverage across the rest of the business logic
- Implement a database to persist the artwork data
- Implement pagination on the artwork list allowing the scrollable list to continue fetching new results once reaching the end
- Fill out the design of the app (header, page titles etc.)
- Add UI tests 

# Additional notes
The specification calls for the user to be able to navigate back from the details screen to the list - no specific button was added to do this as that has a more iOS design look in my opinion. 
Instead we rely on backstack navigation and the android native back button that users have access to. 

Some of the artworks do not seem to be available such as "Bearded Old Man Leanig Forward, from the Large Oriental Heads" https://www.artic.edu/iiif/2/a77a2b21-987b-3031-0894-f6a64827/full/100,/0/default.jpg - 
I have checked these calls in a regular browser and the issue is appearing there also, so I believe this is an api issue (although if you find otherwise and can see something I've done wrong here please let me 
know, I would be very interested in correcting it). 

# Screenshots
![Capture](https://github.com/user-attachments/assets/31b008b0-d620-4e7b-9657-24d296ddd5a3)
![Capture2](https://github.com/user-attachments/assets/9d1356d9-ce1e-406a-8e12-d8a8d29f1d8d)

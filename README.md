# GreedyImageEngine-
A powerful Image Loading Library

# Features:

* All the Images will be loaded asynchronously to Image views.
* Supports both Memory & Disk level Caching which enables faster, snappier & smooth loading & scrolling experience.

# Usuage:

## Step 1: Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```
  allprojects {
	repositories {
	...
		maven { url 'https://jitpack.io' }
		}
	}
  ```
  
 ## Step 2: Add the dependency.
 ```
 dependencies {
	        implementation 'com.github.MohanSaiManthri:GreedyImageEngine-:Tag'
	}
  ```
  
  ## Step 3: 
  
  ```
          ImageLoader
            .with(holder.imageView.context)
            .load(listOfItems[position].url,
                /*placeholder*/R.drawable.place_holder,
                imageView)
 ```
 
 ## Heading towards:
  * Able to cancel onFlight request.
  * Gif Support.
  
 # Thanks 
  * Mohan Sai Manthri
  
 # Library Flow:
 
 * ImageLoader receives a request which contains an ImageView and Image URL
 * Check whether Image exist in cache or not
 * If Image exists in the cache, then scale the Image to the required size and load it onto ImageView.
 * If Image doesn't exist in the cache, then Download the Image, write it to the cache and execute Step 3
 
 ## For Caching
 * There are two choices available for caching. One is Memory cache and another one is Disk Cache.
 * In Memory cache, data is stored in the application memory and have faster read rate but data will get cleared once the application      got killed. 
 * In Disk cache, data is stored and persisted in the disk but it has slower read time compared to the Memory cache.
 
 ## For Background Execution: 
 * ImageLoader will receive multiple image load request. At that time, it needs to have a thread pool to handle multiple image download.
 * In our example, we use ExecutorService for handling background task.
 
 ## For Handling Duplicate Request: 
 * Sometimes, an ImageView may make more than one request[ E.g. ImageView in a RecyclerView]. Downloading Image or loading Image from      the cache for every request is unnecessary.
 * To avoid that, ImageLoader will store all the request details which will be used to find out whether any duplicate request is made or   not.
 * To save request detail, we will use WeakHashMap<ImageView, String[ImageUrl]>

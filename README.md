# CustomRatingBar

`CustomRatingBar` is a customizable Android Layout that provides a rating bar with animated icons. It allows you to set up a rating system with custom icons, colors, and animations. Developers can also receive notifications when the rating changes through a listener.

## Features

- **Customizable Icons**: Set your own drawable icons for the rating stars.
- **Customizable Sizes**: Define the size of the icons.
- **Animations**: Choose from scale or rotation animations for interactive feedback.
- **Color Customization**: Customize the color of selected and unselected icons.
- **Rating Change Listener**: Notify when the rating changes with a callback.

## Usage

1. **Add to Layout XML**

   Add `CustomRatingBar` to your XML layout file:

   ```xml
   <com.gilazani.customratingbarmodule.CustomRatingBar
       android:id="@+id/customRatingBar"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       app:iconDrawable="@drawable/your_icon"
       app:iconSize="48dp"
       app:ratingCount="5"
       app:animationStyle="1"
       app:selectedColor="@color/selected_color"
       app:unselectedColor="@color/unselected_color"/>
   ```

   Ensure you have defined the necessary attributes in res/values/attrs.xml.

2. **Setup in Java Code**

```java
CustomRatingBar ratingBar = findViewById(R.id.customRatingBar);

ratingBar.setOnRatingChangeListener(new CustomRatingBar.OnRatingChangeListener() {
    @Override
    public void onRatingChanged(int newRating) {
        // Handle the rating change
        Log.d("CustomRatingBar", "New Rating: " + newRating);
    }
});
```

Attributes
----------

* `iconDrawable`: Drawable resource for the rating icons. (required)
* `iconSize`: Size of the icons in pixels or dp.
  * **Default**: `50dp`
* `ratingCount`: Number of rating icons.
  * **Default**: `5`
* `animationStyle`: Style of animation (None/Scale/Rotate).
  * **Default**: None
* `selectedColor`: Color for the selected rating icons.
  * **Default**: default selected color
* `unselectedColor`: Color for the unselected rating icons.
  * **Default**: default unselected color

Custom Listener
---------------

To receive notifications when the rating changes, implement the `OnRatingChangeListener` interface:

```java
public interface OnRatingChangeListener {
    void onRatingChanged(int newRating);
}
```

Set the listener using:
```java
ratingBar.setOnRatingChangeListener(new CustomRatingBar.OnRatingChangeListener() {
    @Override
    public void onRatingChanged(int newRating) {
        // Handle the rating change
    }
});
```

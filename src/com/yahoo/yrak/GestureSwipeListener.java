package com.yahoo.yrak;

import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


/**
 * Basic implementation to handle swipe gestures
 *
 */
public class GestureSwipeListener implements OnTouchListener {

	private final GestureDetector gestureDetector = new GestureDetector(new GestureListener());
	private boolean swiped =false;

	@Override
	public boolean onTouch(final View view, final MotionEvent motionEvent) {
		return gestureDetector.onTouchEvent(motionEvent);
	}

	/**
	 * Implements onFling method to handle up/down/right/left swipes
	 *
	 */
	private final class GestureListener extends SimpleOnGestureListener {

		private static final int MIN_SWIPE = 100;
		private static final int MIN_VELOCITY = 100;


		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}

		/**
		 * Takes motionevents and calculates the type of swipe based on distance and velocity covered by swipe
		 * 
		 * @param e1 -- The first down motion event that started the fling.
		 * @param e2 -- The move motion event that triggered the current onFling.
		 * @param velocityX -- The velocity of this fling measured in pixels per second along the x axis.
		 * @param velocityY -- The velocity of this fling measured in pixels per second along the y axis.

		 */

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

			float distanceY  = e2.getY() - e1.getY();
			float distanceX  = e2.getX() - e1.getX();


			boolean changeInX = Math.abs(distanceX) > Math.abs(distanceY);
			boolean changeInY = Math.abs(distanceY) > Math.abs(distanceX);

			boolean xFling = changeInX && (Math.abs(distanceX) > MIN_SWIPE) && (Math.abs(velocityX) > MIN_VELOCITY) ;
			boolean yFling = changeInY && (Math.abs(distanceY) > MIN_SWIPE) && (Math.abs(velocityY) > MIN_VELOCITY) ;


			if (xFling){
				swiped = true;
				if (distanceX>0){
					onSwipeLeft();
				}
				else if (distanceX<0){
					onSwipeRight();
				}
			}else if (yFling){
				swiped = true;

				if (distanceY>0){
					onSwipeUp();
				}
				else if (distanceY<0){
					onSwipeDown();
				}
			}else{
				swiped = false;
			}

			return swiped;

		}
	}

	public void onSwipeRight() {
	}

	public void onSwipeLeft() {
	}

	public void onSwipeUp() {
	}

	public void onSwipeDown() {
	}
}
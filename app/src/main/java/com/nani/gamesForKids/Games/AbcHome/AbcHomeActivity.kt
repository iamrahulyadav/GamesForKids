package com.nani.gamesForKids.Games.AbcHome
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.view.MotionEvent
import android.view.View
import com.nani.gamesForKids.Core.FullScreenGameActivity

import com.nani.gamesForKids.R
import kotlinx.android.synthetic.main.activity_abc_home.*
import java.util.*

class AbcHomeActivity : FullScreenGameActivity(), AbcHomeBoardView.AbcBoardViewListener, View.OnTouchListener, TextToSpeech.OnInitListener {
    var letterCircles: MutableList<LetterCircle> = arrayListOf()
    var home: Home? = null
    var letterCircleIndex = 0
    var textToSpeech: TextToSpeech? = null
    var textToSpeechLoaded = false
    var lastLetterDisplayed: Boolean = false
    var shouldCatch: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abc_home)

        textToSpeech = TextToSpeech(this, this)
    }

    override fun onDestroy() {
        textToSpeech!!.stop()
        textToSpeech!!.shutdown()

        super.onDestroy()
    }

    fun populateList(radius: Float) {
        clear()
        home = Home(abcBoardView.width.toFloat(), abcBoardView.height.toFloat(), radius);
        letterCircles = LetterCirclesHelper(home!!.getList(), radius).letterCircles
    }

    fun clear() {
        letterCircleIndex = 0
        abcBoardView.resetPath()
    }

    override fun boardViewSizeChanged() {
        populateList(abcBoardView.width / 10.0f)
        showNextLetter()
        abcBoardView.setOnTouchListener(this)
    }

    fun showNextLetter() {

        if (lastLetterDisplayed) {
            finishGame()
        }
        else {
            showLetter()
        }
    }

    fun showLetter()  {
        abcBoardView.setCircle(letterCircles[letterCircleIndex])
        letterCircleIndex++

        if (isItLastLetter()) {
            lastLetterDisplayed = true
        }
    }

    fun isItLastLetter(): Boolean {
        return letterCircleIndex == letterCircles.size
    }

    fun finishGame() {
        shouldCatch = false
        finishGameView()
        finishGameSpeak()
        homeTextView.visibility = View.VISIBLE
    }

    fun finishGameSpeak() {
        textToSpeech!!.setSpeechRate(0.5f)
        textToSpeech!!.speak(resources.getString(R.string.it_is_a_home), TextToSpeech.QUEUE_ADD, null)
    }

    fun finishGameView() {
        abcBoardView.letterCircle = null
        abcBoardView.home = home

        abcBoardView.invalidate()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        if (textToSpeechLoaded && shouldCatch && clicked(event) && caughtCircle(event)) {
            textToSpeech!!.speak(abcBoardView.letterCircle!!.letter.toString(), TextToSpeech.QUEUE_FLUSH, null)
            showNextLetter()
        }

        return true;
    }

    fun clicked(event: MotionEvent?): Boolean {
        return isDown(event) || event!!.action == MotionEvent.ACTION_MOVE;
    }

    fun isDown(event: MotionEvent?): Boolean {
        return (event!!.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_POINTER_DOWN)
    }

    fun caughtCircle(event: MotionEvent?): Boolean {
        var i: Int = 0
        while (i < event!!.pointerCount) {

            if (abcBoardView.letterCircle!!.getRectForCircle().contains(event.getX(i).toInt(), event.getY(i).toInt())) {
                return true
            }

            i++
        }

        return false
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            textToSpeech!!.language = Locale.UK

            var hashMap = HashMap<String, String>();
            hashMap.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "");
            textToSpeech!!.speak(" ", TextToSpeech.QUEUE_FLUSH, hashMap)
            textToSpeech!!.setOnUtteranceProgressListener(object: UtteranceProgressListener() {

                override fun onError(utteranceId: String?) {
                    throw UnsupportedOperationException()
                }

                override fun onStart(utteranceId: String?) {

                }

                override fun onDone(utteranceId: String?) {
                    runOnUiThread(object : Runnable {
                        public override fun run() : Unit {
                            progressBar.visibility = View.GONE
                            textToSpeechLoaded = true
                        }
                    })
                }
            })
        }
    }
}

package com.rxiong.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {


    lateinit var questionsList:ArrayList<QuestionModel>
    private var index:Int = 0
    lateinit var questionModel: QuestionModel

    private var correctAnswerCount:Int=0
    private var wrongAnswerCount:Int=0

    lateinit var countDown:TextView
    lateinit var questions:TextView
    lateinit var option1:Button
    lateinit var option2:Button
    lateinit var option3:Button
    lateinit var option4:Button




    private var backPressedTime: Long = 0
    private var backToast: Toast? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        countDown=findViewById(R.id.countdown)
        questions=findViewById(R.id.questions)
        option1=findViewById(R.id.option1)
        option2=findViewById(R.id.option2)
        option3=findViewById(R.id.option3)
        option4=findViewById(R.id.option4)




        questionsList= ArrayList()
        questionsList.add(QuestionModel("What was the highest-grossing movie of the past year?","Avengers: Endgame","Spider-Man: No Way Home","Barbie"," Oppenheimer","Barbie"))
        questionsList.add(QuestionModel("Who won the Academy Award for Best Actor in the 2023 Oscars ceremony?","Joaquin Phoenix","Anthony Hopkins","Leonardo DiCaprio","Brendan Fraser","Brendan Fraser"))
        questionsList.add(QuestionModel("Which streaming platform is home to the TV series \"Stranger Things\"?","Netflix","Hulu","Amazon Prime Video","Disney+","Netflix"))
        questionsList.add(QuestionModel("What artist or band sang the song \"Blinding Lights\"?","Taylor Swift","Billie Eilish","The Weeknd"," BTS","The Weeknd"))
        questionsList.add(QuestionModel("What major cultural event occurs annually in San Diego and is known for its focus on comics, movies, and TV shows?","Cannes Media Festival","Sundance Arts Festival","Comic-Con International","SXSW","Comic-Con International"))
        questionsList.add(QuestionModel("Which film won the Best Picture category at the most recent Oscars?","Parasite","Everything Everywhere All At Once","The Shape of Water","La La Land","Everything Everywhere All At Once"))
        questionsList.add(QuestionModel("In which year was the first episode of the TV series \"Friends\" aired?","1994","1996","1998","2000","1994"))
        questionsList.add(QuestionModel("Which classic novel was adapted into a popular TV series featuring Henry Cavill as the lead character?","The Witcher","Game of Thrones","Outlander","The Handmaid's Tale","The Witcher"))
        questionsList.add(QuestionModel("Who is known for their role as Wanda Maximoff in the Marvel Cinematic Universe?","Scarlett Johansson","Elizabeth Olsen","Brie Larson","Gal Gadot","Elizabeth Olsen"))
        questionsList.add(QuestionModel("What social media platform is famous for its short-form video content and is particularly popular among younger audiences?","Twitter","Snapchat","TikTok","Instagram","TikTok"))
        questionsList.add(QuestionModel("Who played the lead role in the 2019 film \"Joker\"?","Leonardo DiCaprio","Joaquin Phoenix","Robert Pattinson","Tom Hardy","Joaquin Phoenix"))
        questionsList.add(QuestionModel("Which of the following is a song by Billie Eilish?","Shape of You","Bad Guy","Old Town Road","Uptown Funk","Bad Guy"))
        questionsList.add(QuestionModel("Who won the Album of the Year at the 2020 Grammy Awards?","Taylor Swift","Beyoncé","Adele","Bruno Mars","Taylor Swift"))
        questionsList.add(QuestionModel("Who directed the film \"La La Land\" (2016)?","Christopher Nolan","Damien Chazelle","Quentin Tarantino","Steven Spielberg","Damien Chazelle"))
        questionsList.add(QuestionModel("What is the title of Taylor Swift's debut album?","Fearless","Speak Now","Taylor Swift","Red","Taylor Swift"))
        questionsList.add(QuestionModel("Which actress played the character Katniss Everdeen in \"The Hunger Games\" film series?","Emma Watson","Jennifer Lawrence","Kristen Stewart","Scarlett Johansson","Jennifer Lawrence"))
        questionsList.add(QuestionModel("Who is the lead vocalist of the band Coldplay?","Chris Martin","Thom Yorke","Bono","Eddie Vedder","Chris Martin"))
        questionsList.add(QuestionModel("Who played the character Jack Dawson in the film \"Titanic\"?","Leonardo DiCaprio","Tom Hanks","Johnny Depp","Brad Pitt","Leonardo DiCaprio"))
        questionsList.add(QuestionModel("Which pop star is known for her alter ego \"Sasha Fierce\"?","Rihanna","Lady Gaga","Beyoncé","Katy Perry","Beyoncé"))
        questionsList.add(QuestionModel("What is the name of the fictional wizarding sport played on broomsticks in the \"Harry Potter\" series?","Quidditch","Bludgerball","Wizardball","Broomstick Polo","Quidditch"))
        questionsList.add(QuestionModel("Who played Peeta Mellark in the “Hunger Games” franchise?","Leonardo DiCaprio","Liam Hemsworth","Josh Hutcherson","Chris Hemsworth","Josh Hutcherson"))
        questionsList.add(QuestionModel("Which animated film features a character named Simba?","Shrek","The Lion King","Finding Nemo","Aladdin","The Lion King"))
        questionsList.add(QuestionModel("What is the name of the fictional city in which Batman operates?","Gotham City","Metropolis","Central City","Star City","Gotham City"))

        questionsList.shuffle()
        questionModel= questionsList[index]

        setAllQuestions()

        countdown()










    }


    fun countdown(){
        var duration:Long=TimeUnit.SECONDS.toMillis(7)


        object :CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                var sDuration:String= String.format(Locale.ENGLISH,
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))

                countDown.text = sDuration

            }
            override fun onFinish() {
                index++
                if (index<5){
                    questionModel=questionsList[index]
                    setAllQuestions()
                    resetBackground()
                    enableButton()
                    countdown()

                }
                else{

                    gameResult()

                }


            }



        }.start()



    }


    private fun correctAns(option: Button){
        option.background=getDrawable(R.drawable.right_bg)

        correctAnswerCount++



    }
    private fun wrongAns(option:Button){

        option.background=resources.getDrawable(R.drawable.wrong_bg)

        wrongAnswerCount++


    }

    private fun gameResult(){
        var intent=Intent(this,ResultActivity::class.java)

        intent.putExtra("correct",correctAnswerCount.toString())
        intent.putExtra("total","5")

        startActivity(intent)
    }



    private fun setAllQuestions() {
        questions.text=questionModel.question
        option1.text=questionModel.option1
        option2.text=questionModel.option2
        option3.text=questionModel.option3
        option4.text=questionModel.option4
    }
    private fun enableButton(){
        option1.isClickable=true
        option2.isClickable=true
        option3.isClickable=true
        option4.isClickable=true
    }
    private fun disableButton(){
        option1.isClickable=false
        option2.isClickable=false
        option3.isClickable=false
        option4.isClickable=false
    }
    private fun resetBackground(){
        option1.background=resources.getDrawable(R.drawable.option_bg)
        option2.background=resources.getDrawable(R.drawable.option_bg)
        option3.background=resources.getDrawable(R.drawable.option_bg)
        option4.background=resources.getDrawable(R.drawable.option_bg)
    }
    fun option1Clicked(view:View){
        disableButton()
        if(questionModel.option1==questionModel.answer){
            option1.background=resources.getDrawable(R.drawable.right_bg)


            correctAns(option1)

        }
        else{
            wrongAns(option1)
        }
    }

    fun option2Clicked(view:View){
        disableButton()
        if(questionModel.option2==questionModel.answer){
            option2.background=resources.getDrawable(R.drawable.right_bg)


            correctAns(option2)

        }
        else{
            wrongAns(option2)
        }
    }
    fun option3Clicked(view:View){
        disableButton()
        if(questionModel.option3==questionModel.answer){

            option3.background=resources.getDrawable(R.drawable.right_bg)


            correctAns(option3)


        }
        else{
            wrongAns(option3)
        }
    }
    fun option4Clicked(view:View){
        disableButton()
        if(questionModel.option4==questionModel.answer){
            option4.background=resources.getDrawable(R.drawable.right_bg)


            correctAns(option4)

        }
        else{
            wrongAns(option4)
        }
    }

    override fun onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast?.cancel()
            finish()
        }

        else {
            backToast = Toast.makeText(baseContext, "DOUBLE PRESS TO QUIT GAME", Toast.LENGTH_SHORT)
            backToast?.show()
        }
        backPressedTime = System.currentTimeMillis()

    }




}
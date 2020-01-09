//Detta jobbar vi vidare med  (Matchning)
const textAreaElement = document.getElementById("text")
textAreaElement.addEventListener("input", () =>{
    const arrayQuote = quoteDisplay.querySelectorAll("span")
    const arrayValue = textAreaElement.value.split("")
    arrayQuote.forEach((characterSpan, index) => {
        const character = arrayValue[index]
        if (character === characterSpan.innerText){
            characterSpan.classList.add("correct")
        } else{
            characterSpan.classList.add("incorrect")
        }
    })
})
function splitQuote(){
        const quoteDisplay = document.getElementById("quoteDisplay")
        quoteDisplay.split("").foreach(character =>{
        const characterSpan = document.createElement("span")
        characterSpan.classList.add("correct")
        quoteDisplay.appendChild(characterSpan)
    })


}
splitQuote()
//Detta jobbar vi vidare med  (Matchning)..
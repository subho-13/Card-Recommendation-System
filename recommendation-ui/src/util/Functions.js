function beautify (word) {
    const wordArray = word.split('_')
    const convertedWordArray = wordArray.map(word => {
        var newWord = word.toLowerCase()
        return newWord.charAt(0).toUpperCase() + newWord.slice(1)
    })

    return convertedWordArray.join(' ')
}

export default beautify
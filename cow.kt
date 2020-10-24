import java.io.File


fun readCOW(path: String): String{
    val file = File(path)
    val lines = file.readLines()
    return lines.joinToString(" ")
}


fun getLoopBlocks(source: ArrayList<String>): HashMap<Int,Int> {
    val blocks = HashMap<Int,Int>()
    val stack = mutableListOf<Int>()
    for ((i, char) in source.withIndex()){
        if (char =="MOO"){
            stack.add(i)

        }
        if(char == "moo"){
            blocks[i] = stack[stack.lastIndex]
            blocks[stack.removeAt(stack.lastIndex)] = i

        }

    }
    return blocks

}



fun eval(source: String){

    val sourc = ArrayList<String>(source.split(' '))
    val buffer = Array<Char> (2000) {_ -> (0).toChar()}
    var ptr = 0
    var i = 0

    val blocks=getLoopBlocks(sourc)

    while (i < sourc.size){
        when (sourc[i]){
            "MoO" -> buffer[ptr] = buffer[ptr] + 1
            "MOo" -> buffer[ptr] = buffer[ptr] - 1
            "moO" -> ptr +=1
            "mOo" -> ptr -=1
            "OOM" ->print("${buffer[ptr]}")
            "oom" ->{
            print(">>>")
            buffer[ptr] == readLine()?.toCharArray()?.get(0)
        }
            "mOO" -> {}
            "OOO" -> buffer[ptr] = '0'
            "Moo" -> {
                if (buffer[ptr] == (0).toChar()) {
                print(">>>")
                buffer[ptr] = readLine()?.toCharArray()?.get(0)!!}
                else{
                    print(buffer[ptr])
                }

            }
            "MOO" -> {
                if (buffer[ptr] == (0).toChar()){
                    i = blocks[i]!!
                }
            }
            "moo" -> {
                if (buffer[ptr] != (0).toChar()) {
                    i = blocks[i]!!
                }
            }
                else -> {
                    if (i + 1 < sourc.size && sourc[i + 1] == ":") println()
                    print(sourc[i] + " ")

                }

        }
        i+=1
    }

}

fun main(args: Array<String>){
    val source = readCOW("C://brainfuck//hello.cow")
    println(source)
    eval(source)
    println("\n\nDone")
}

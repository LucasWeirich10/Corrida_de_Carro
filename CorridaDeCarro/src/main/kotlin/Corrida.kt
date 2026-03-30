import kotlin.random.Random  //gera numeros aleatorios para a corrida

class Piloto(val nome: String, val idade: Int, val poder: String)

class Carro(
    val modelo: String,
    val velocidade: Int,
    val aceleracao: Int,
    val freio: Int
)

class Pista(
    val pilotos: List<Piloto>,
    val carros: List<Carro>,
    val voltas: Int
) {

    fun efeitoPoder(poder: String): Int {  //muda o nome do poder pra um numero
        return when (poder.lowercase()) {
            "turbo" -> 30
            "banana" -> -20
            "oleo" -> -10
            "foguete" -> 25
            else -> 0
        }
    }

    fun correr() {
        val resultados = mutableMapOf<String, Int>()

        println("Digite START para iniciar a corrida!")
        readLine()

        for (i in pilotos.indices) { //podio dos pilotos
            val piloto = pilotos[i]
            val carro = carros[i]

            var pontos = 0

            println("\n🚗 ${piloto.nome} com ${carro.modelo} começou!")

            repeat(voltas) { volta ->
                val base = carro.velocidade + carro.aceleracao + carro.freio
                val usouPoder = Random.nextInt(100) < 50
                val bonusPoder = if (usouPoder) efeitoPoder(piloto.poder) else 0



                val desempenho = base + bonusPoder
                pontos += desempenho

               if (usouPoder) {
                   println("Volta ${volta + 1}: ${piloto.nome} usou ${piloto.poder} -> $bonusPoder pontos")
               } else {
                   println("Volta ${volta + 1}: ${piloto.nome} NÃO usou poder")
               }
            }

            resultados[piloto.nome] = pontos
        }

        val ranking = resultados.toList().sortedByDescending { it.second }

        println("\n🏁 Placar Final:\n")
        ranking.forEachIndexed { i, (nome, pontos) ->

            val index = pilotos.indexOfFirst { it.nome == nome }
            val carro = carros[index]

            println("${i + 1}º lugar: $nome (${carro.modelo}) - $pontos pontos")
        }
    }
}

fun main() {
    val pilotos = listOf(
        Piloto("Lucas", 18, "turbo"),
        Piloto("Jairzinho", 43, "oleo"),
        Piloto("Nicolau", 89, "banana")
    )

    val carros = listOf(
        Carro("Lancer", 90, 110, 80),
        Carro("Camaro", 130, 80, 90),
        Carro("Supra", 190, 130, 100)
    )

    val pista = Pista(pilotos, carros, voltas = 3)
    pista.correr()
}
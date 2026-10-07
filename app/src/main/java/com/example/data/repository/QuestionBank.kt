package com.example.data.repository

import com.example.data.model.Difficulty
import com.example.data.model.Question
import com.example.data.model.Subject

object QuestionBank {

    private val allQuestions: List<Question> = listOf(
        // ================= MATEMÁTICA (22 Perguntas) =================
        // 6-7 anos
        Question(
            id = "mat_1",
            question = "Quanto é 3 + 2 maçãs?",
            options = listOf("4", "5", "6", "7"),
            correctAnswer = "5",
            explanation = "Juntando 3 maçãs com mais 2 maçãs temos 5 maçãs!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "mat_2",
            question = "Você tem 6 brinquedos e dá 2 para seu amigo. Quantos sobraram?",
            options = listOf("3", "4", "5", "6"),
            correctAnswer = "4",
            explanation = "6 tirando 2 fica igual a 4 brinquedos!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "mat_3",
            question = "Qual número vem logo depois do número 9?",
            options = listOf("8", "10", "11", "12"),
            correctAnswer = "10",
            explanation = "A sequência é: 7, 8, 9 e depois vem o 10!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "mat_4",
            question = "Quantos lados tem um triângulo?",
            options = listOf("2 lados", "3 lados", "4 lados", "5 lados"),
            correctAnswer = "3 lados",
            explanation = "O triângulo possui exatamente 3 lados e 3 pontas!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "mat_5",
            question = "Quanto é 5 + 5?",
            options = listOf("8", "9", "10", "12"),
            correctAnswer = "10",
            explanation = "5 dedinhos em uma mão mais 5 na outra formam 10 dedinhos!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "mat_6",
            question = "Qual dessas formas geométricas parece com uma bola de futebol?",
            options = listOf("Círculo", "Quadrado", "Triângulo", "Retângulo"),
            correctAnswer = "Círculo",
            explanation = "A bola de futebol é redondinha como um círculo!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "mat_7",
            question = "Se uma dezena tem 10 unidades, quantas unidades têm 2 dezenas?",
            options = listOf("12", "15", "20", "25"),
            correctAnswer = "20",
            explanation = "2 dezenas são 10 + 10, que dá 20 unidades!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),

        // 8-9 anos
        Question(
            id = "mat_8",
            question = "Quanto é 4 x 3 lápis?",
            options = listOf("7", "10", "12", "14"),
            correctAnswer = "12",
            explanation = "4 vezes 3 é o mesmo que 3 + 3 + 3 + 3 = 12!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "mat_9",
            question = "Se dividirmos 15 balas igualmente entre 3 crianças, quantas cada uma ganha?",
            options = listOf("3 balas", "4 balas", "5 balas", "6 balas"),
            correctAnswer = "5 balas",
            explanation = "15 dividido por 3 é 5 balas para cada criança!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "mat_10",
            question = "Qual número é o dobro de 8?",
            options = listOf("14", "16", "18", "20"),
            correctAnswer = "16",
            explanation = "O dobro de um número é ele vezes 2. Logo, 8 x 2 = 16!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "mat_11",
            question = "Quantos minutos tem meia hora?",
            options = listOf("15 minutos", "30 minutos", "45 minutos", "60 minutos"),
            correctAnswer = "30 minutos",
            explanation = "Uma hora inteira tem 60 minutos, então a metade são 30 minutos!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "mat_12",
            question = "Quanto é 7 x 6?",
            options = listOf("40", "42", "45", "48"),
            correctAnswer = "42",
            explanation = "Na tabuada do 7: 7 x 6 é igual a 42!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "mat_13",
            question = "Qual é o valor de 50 - 18?",
            options = listOf("30", "32", "34", "38"),
            correctAnswer = "32",
            explanation = "50 menos 18 é igual a 32!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "mat_14",
            question = "Uma dúzia de ovos são 12 ovos. Quantos ovos são meia dúzia?",
            options = listOf("4 ovos", "6 ovos", "8 ovos", "10 ovos"),
            correctAnswer = "6 ovos",
            explanation = "A metade de 12 é 6 ovos!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),

        // 10 anos
        Question(
            id = "mat_15",
            question = "Lucas comprou 3 cadernos por R$ 9 cada um. Quanto ele pagou no total?",
            options = listOf("R$ 18", "R$ 24", "R$ 27", "R$ 30"),
            correctAnswer = "R$ 27",
            explanation = "3 cadernos x R$ 9 = R$ 27 no total!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "mat_16",
            question = "Qual é a fração que representa a metade de uma pizza cortada em 4 pedaços?",
            options = listOf("1/4", "2/4", "3/4", "4/4"),
            correctAnswer = "2/4",
            explanation = "2 pedaços de um total de 4 representam a metade (2/4)!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "mat_17",
            question = "Quantos centímetros há em 2 metros?",
            options = listOf("20 cm", "100 cm", "200 cm", "2000 cm"),
            correctAnswer = "200 cm",
            explanation = "Cada metro tem 100 cm, logo 2 metros têm 200 centímetros!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "mat_18",
            question = "Qual é o resultado de 9 x 8?",
            options = listOf("64", "71", "72", "81"),
            correctAnswer = "72",
            explanation = "9 vezes 8 é igual a 72!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "mat_19",
            question = "Um trem parte com 120 passageiros. Na primeira parada descem 35 e sobem 15. Quantos estão no trem?",
            options = listOf("95", "100", "105", "110"),
            correctAnswer = "100",
            explanation = "120 - 35 = 85. Depois 85 + 15 = 100 passageiros!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "mat_20",
            question = "Qual é a área de um quadrado cujo lado mede 5 metros?",
            options = listOf("20 m²", "25 m²", "30 m²", "10 m²"),
            correctAnswer = "25 m²",
            explanation = "A área do quadrado é lado x lado: 5 x 5 = 25 m²!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "mat_21",
            question = "Quanto é 100 dividido por 4?",
            options = listOf("20", "25", "30", "50"),
            correctAnswer = "25",
            explanation = "100 dividido em 4 partes iguais dá 25 para cada parte!",
            subject = Subject.MATEMATICA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),

        // ================= PORTUGUÊS (21 Perguntas) =================
        // 6-7 anos
        Question(
            id = "por_1",
            question = "Qual palavra rima com a palavra GATO?",
            options = listOf("RATO", "BOLA", "CASA", "PEIXE"),
            correctAnswer = "RATO",
            explanation = "Gato e Rato terminam com o mesmo sonzinho final: -ATO!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "por_2",
            question = "Com qual letrinha começa a palavra ABELHA?",
            options = listOf("B", "A", "E", "O"),
            correctAnswer = "A",
            explanation = "A de Abelha, de Amigo e de Amor!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "por_3",
            question = "Quantas sílabas (pedacinhos) tem a palavra PIPOCA?",
            options = listOf("2 sílabas", "3 sílabas", "4 sílabas", "1 sílaba"),
            correctAnswer = "3 sílabas",
            explanation = "Batendo palmas: PI - PO - CA! São 3 pedacinhos!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "por_4",
            question = "Qual é o contrário (antônimo) de GRANDE?",
            options = listOf("Gordo", "Pequeno", "Alto", "Bonito"),
            correctAnswer = "Pequeno",
            explanation = "O oposto de algo grande é algo pequeno!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "por_5",
            question = "Quantas vogais existem no alfabeto?",
            options = listOf("3 vogais", "5 vogais", "7 vogais", "10 vogais"),
            correctAnswer = "5 vogais",
            explanation = "As 5 vogais são: A, E, I, O e U!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "por_6",
            question = "Qual dessas palavras está escrita corretamente?",
            options = listOf("SAPO", "CZAPO", "SSAPO", "CAPO"),
            correctAnswer = "SAPO",
            explanation = "Sapo se escreve com a letra S no início!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "por_7",
            question = "Qual é a primeira letra do nosso alfabeto?",
            options = listOf("B", "Z", "A", "M"),
            correctAnswer = "A",
            explanation = "O alfabeto sempre começa na letra A!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),

        // 8-9 anos
        Question(
            id = "por_8",
            question = "Qual das palavras abaixo é um SUBSTANTIVO (nome de algo)?",
            options = listOf("Correr", "Cachorro", "Felizmente", "Muito"),
            correctAnswer = "Cachorro",
            explanation = "Cachorro dá nome a um animal, por isso é um substantivo!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "por_9",
            question = "Qual palavra é um ADJETIVO (qualidade/característica)?",
            options = listOf("Carro", "Pular", "Brilhante", "Ontem"),
            correctAnswer = "Brilhante",
            explanation = "Brilhante indica uma qualidade de algo (ex: estrela brilhante)!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "por_10",
            question = "Qual é o plural correto da palavra BALÃO?",
            options = listOf("Balãos", "Balões", "Balães", "Balonzes"),
            correctAnswer = "Balões",
            explanation = "Um balão, muitos balões!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "por_11",
            question = "Qual pontuação usamos no final de uma pergunta?",
            options = listOf("Ponto final (.)", "Ponto de interrogação (?)", "Ponto de exclamação (!)", "Vírgula (,)"),
            correctAnswer = "Ponto de interrogação (?)",
            explanation = "Sempre que fazemos uma pergunta usamos a interrogação (?)!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "por_12",
            question = "Qual é o antônimo (oposto) da palavra CORAJOSO?",
            options = listOf("Forte", "Medroso", "Alegre", "Rápido"),
            correctAnswer = "Medroso",
            explanation = "Quem não é corajoso sente medo, portanto é medroso!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "por_13",
            question = "Na frase 'O menino comeu uma maçã doce', qual é o VERBO (ação)?",
            options = listOf("menino", "comeu", "maçã", "doce"),
            correctAnswer = "comeu",
            explanation = "'Comeu' indica a ação que o menino realizou!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "por_14",
            question = "Qual palavra é sinônimo (significado parecido) de ALEGRE?",
            options = listOf("Triste", "Contente", "Bravo", "Cansado"),
            correctAnswer = "Contente",
            explanation = "Estar alegre é o mesmo que estar contente ou feliz!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),

        // 10 anos
        Question(
            id = "por_15",
            question = "Qual das palavras abaixo é proparoxítona (sílaba mais forte na antepenúltima)?",
            options = listOf("Café", "Lâmpada", "Jardim", "Papel"),
            correctAnswer = "Lâmpada",
            explanation = "LÂM-pa-da tem a antepenúltima sílaba mais forte e leva acento!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "por_16",
            question = "Complete a frase: 'Eles ______ ao parque ontem à tarde.'",
            options = listOf("vão", "foram", "irão", "iria"),
            correctAnswer = "foram",
            explanation = "Como a ação aconteceu ontem (passado), usamos 'foram'!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "por_17",
            question = "Na frase 'Mariana encontrou um lindo pássaro', qual é o sujeito?",
            options = listOf("encontrou", "Mariana", "lindo", "pássaro"),
            correctAnswer = "Mariana",
            explanation = "O sujeito é quem pratica a ação descrita: Mariana!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "por_18",
            question = "Qual palavra contém um encontro consonantal (duas consoantes juntas)?",
            options = listOf("PRATO", "BOLA", "CASA", "FADA"),
            correctAnswer = "PRATO",
            explanation = "As consoantes P e R estão juntas no início da palavra!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "por_19",
            question = "Qual é o sentido da expressão popular 'chover no molhado'?",
            options = listOf("Tomar chuva forte", "Repetir o que já é óbvio", "Plantar flores", "Gostar do inverno"),
            correctAnswer = "Repetir o que já é óbvio",
            explanation = "'Chover no molhado' significa insistir em algo que já está claro ou resolvido!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "por_20",
            question = "Qual palavra está grafada com a acentuação correta?",
            options = listOf("Árvore", "Arvore", "Arvorê", "Àrvore"),
            correctAnswer = "Árvore",
            explanation = "Árvore é proparoxítona e deve receber acento agudo no Á!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "por_21",
            question = "Qual é o feminino do substantivo CAVALHEIRO?",
            options = listOf("Amazona", "Dama", "Cavalheira", "Princesa"),
            correctAnswer = "Dama",
            explanation = "O par feminino de cavalheiro (homem gentil) é dama!",
            subject = Subject.PORTUGUES,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),

        // ================= CIÊNCIAS (21 Perguntas) =================
        // 6-7 anos
        Question(
            id = "cie_1",
            question = "De onde as plantinhas bebem a água e nutrientes da terra?",
            options = listOf("Pelas folhas", "Pelas flores", "Pelas raízes", "Pelo vento"),
            correctAnswer = "Pelas raízes",
            explanation = "As raízes ficam debaixo da terra e sugam a água e os minerais!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "cie_2",
            question = "Qual desses animais vive e respira embaixo da água?",
            options = listOf("Gato", "Peixe", "Cachorro", "Pássaro"),
            correctAnswer = "Peixe",
            explanation = "Os peixinhos usam brânquias para respirar na água!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "cie_3",
            question = "Qual órgão do nosso corpo usamos para enxergar as cores?",
            options = listOf("Orelhas", "Olhos", "Nariz", "Mãos"),
            correctAnswer = "Olhos",
            explanation = "Nossos olhos são responsáveis pelo sentido da visão!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "cie_4",
            question = "Qual é a grande estrela que ilumina e aquece a Terra de dia?",
            options = listOf("Lua", "Sol", "Marte", "Estrela Cadente"),
            correctAnswer = "Sol",
            explanation = "O Sol é uma grande estrela que nos dá luz e calor todos os dias!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "cie_5",
            question = "O que as lagartas se transformam depois de saírem do casulo?",
            options = listOf("Borboletas", "Besouros", "Passarinhos", "Formigas"),
            correctAnswer = "Borboletas",
            explanation = "É o processo mágico da metamorfose: a lagarta vira borboleta!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "cie_6",
            question = "Em qual estado físico está o cubo de gelo?",
            options = listOf("Líquido", "Sólido", "Gasoso", "Vapor"),
            correctAnswer = "Sólido",
            explanation = "O gelo é a água congelada no estado sólido!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "cie_7",
            question = "Qual desses alimentos vem diretamente de uma planta?",
            options = listOf("Queijo", "Cenoura", "Ovo", "Leite"),
            correctAnswer = "Cenoura",
            explanation = "A cenoura é uma raiz vegetal comestível deliciosa!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),

        // 8-9 anos
        Question(
            id = "cie_8",
            question = "Como é chamado o processo que as plantas usam para produzir alimento com a luz solar?",
            options = listOf("Digestão", "Fotossíntese", "Respiração", "Transpiração"),
            correctAnswer = "Fotossíntese",
            explanation = "A fotossíntese usa luz, água e gás carbônico para produzir energia!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "cie_9",
            question = "Qual órgão do corpo humano bombeia o sangue para todo o organismo?",
            options = listOf("Pulmão", "Cérebro", "Coração", "Estômago"),
            correctAnswer = "Coração",
            explanation = "O coração bate sem parar para bombear oxigênio e sangue!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "cie_10",
            question = "Como são chamados os animais que se alimentam exclusivamente de plantas?",
            options = listOf("Carnívoros", "Herbívoros", "Onívoros", "Detritívoros"),
            correctAnswer = "Herbívoros",
            explanation = "Herbívoros comem folhas, grama e frutos (como cavalos e coelhos)!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "cie_11",
            question = "Qual é o único satélite natural que orbita o planeta Terra?",
            options = listOf("Sol", "Lua", "Vênus", "Júpiter"),
            correctAnswer = "Lua",
            explanation = "A Lua é o satélite natural que reflete a luz do Sol para nós à noite!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "cie_12",
            question = "Qual gás os seres humanos precisam respirar para viver?",
            options = listOf("Gás Carbônico", "Oxigênio", "Hélio", "Metano"),
            correctAnswer = "Oxigênio",
            explanation = "Nossos pulmões absorvem o oxigênio do ar para nosso corpo funcionar!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "cie_13",
            question = "O que acontece quando a água ferve na chaleira e sobe em fumaça?",
            options = listOf("Solidificação", "Evaporação", "Fusão", "Congelamento"),
            correctAnswer = "Evaporação",
            explanation = "A água passa do estado líquido para o gasoso através da evaporação!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "cie_14",
            question = "Qual classe de animais possui pelos e amamenta seus filhotes com leite?",
            options = listOf("Aves", "Mamíferos", "Répteis", "Anfíbios"),
            correctAnswer = "Mamíferos",
            explanation = "Mamíferos mamam quando filhotes (como cães, gatos, golfinhos e humanos)!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),

        // 10 anos
        Question(
            id = "cie_15",
            question = "Qual é a menor unidade viva fundamental de todos os seres vivos?",
            options = listOf("Molécula", "Célula", "Átomo", "Tecido"),
            correctAnswer = "Célula",
            explanation = "Todos os organismos vivos são formados por uma ou mais células!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "cie_16",
            question = "Qual é a principal camada protetora da atmosfera contra raios ultravioleta do Sol?",
            options = listOf("Camada de Ozônio", "Litosfera", "Troposfera", "Núcleo terrestre"),
            correctAnswer = "Camada de Ozônio",
            explanation = "A camada de ozônio filtra os raios UV prejudiciais do Sol!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "cie_17",
            question = "Qual planeta do Sistema Solar é famoso por seus grandes anéis brilhantes?",
            options = listOf("Marte", "Saturno", "Mercúrio", "Terra"),
            correctAnswer = "Saturno",
            explanation = "Saturno possui impressionantes anéis feitos de poeira e gelo!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "cie_18",
            question = "Como é chamada a energia produzida pelo movimento dos ventos?",
            options = listOf("Energia Solar", "Energia Eólica", "Energia Hidrelétrica", "Energia Nuclear"),
            correctAnswer = "Energia Eólica",
            explanation = "Os aerogeradores usam a força dos ventos para produzir energia eólica!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "cie_19",
            question = "Qual dos materiais abaixo é considerado biodegradável na natureza?",
            options = listOf("Garrafa de plástico", "Casca de banana", "Lata de alumínio", "Pneu"),
            correctAnswer = "Casca de banana",
            explanation = "Alimentos orgânicos como cascas se decompõem naturalmente e viram adubo!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "cie_20",
            question = "Quantos ossos tem aproximadamente o esqueleto de um ser humano adulto?",
            options = listOf("52 ossos", "104 ossos", "206 ossos", "350 ossos"),
            correctAnswer = "206 ossos",
            explanation = "O corpo humano adulto possui cerca de 206 ossos firmes e resistentes!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "cie_21",
            question = "Qual é a fórmula química da água que bebemos?",
            options = listOf("CO2", "H2O", "O2", "NaCl"),
            correctAnswer = "H2O",
            explanation = "H2O significa dois átomos de hidrogênio e um átomo de oxigênio!",
            subject = Subject.CIENCIAS,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),

        // ================= GEOGRAFIA (21 Perguntas) =================
        // 6-7 anos
        Question(
            id = "geo_1",
            question = "Em qual país nós vivemos?",
            options = listOf("Brasil", "França", "Japão", "Austrália"),
            correctAnswer = "Brasil",
            explanation = "Nós moramos no lindo e grande país chamado Brasil!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "geo_2",
            question = "Qual é o nome do nosso planeta no espaço?",
            options = listOf("Sol", "Terra", "Marte", "Lua"),
            correctAnswer = "Terra",
            explanation = "Vivemos no planeta Terra, também carinhosamente chamado de Planeta Azul!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "geo_3",
            question = "Qual instrumento tem uma agulha que sempre aponta para o Norte?",
            options = listOf("Relógio", "Bússola", "Lupa", "Termômetro"),
            correctAnswer = "Bússola",
            explanation = "A bússola usa o magnetismo para sempre apontar para o Norte!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "geo_4",
            question = "Qual cor costuma representar as águas e oceanos nos mapas?",
            options = listOf("Verde", "Azul", "Amarelo", "Vermelho"),
            correctAnswer = "Azul",
            explanation = "A cor azul nos mapas sempre mostra mares, rios, lagos e oceanos!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "geo_5",
            question = "Como é chamada a grande porção de água salgada que banha as praias?",
            options = listOf("Oceano", "Lagoa", "Riacho", "Cachoeira"),
            correctAnswer = "Oceano",
            explanation = "O oceano (ou mar) é a grande extensão de água salgada da Terra!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "geo_6",
            question = "Onde as pessoas costumam morar em apartamentos altos e usar metrô?",
            options = listOf("Na cidade", "Na fazenda", "Na floresta", "Na montanha"),
            correctAnswer = "Na cidade",
            explanation = "A cidade (área urbana) tem muitos prédios, carros e avenidas!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "geo_7",
            question = "Qual é o maior bioma com a maior floresta tropical do Brasil?",
            options = listOf("Amazônia", "Caatinga", "Pampa", "Cerrado"),
            correctAnswer = "Amazônia",
            explanation = "A Floresta Amazônica é a maior floresta tropical do mundo!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),

        // 8-9 anos
        Question(
            id = "geo_8",
            question = "Qual é a capital oficial do Brasil?",
            options = listOf("São Paulo", "Rio de Janeiro", "Brasília", "Salvador"),
            correctAnswer = "Brasília",
            explanation = "Brasília é a capital federal do Brasil, localizada no Distrito Federal!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "geo_9",
            question = "Quantas regiões oficiais compõem o território brasileiro?",
            options = listOf("3 regiões", "5 regiões", "7 regiões", "10 regiões"),
            correctAnswer = "5 regiões",
            explanation = "Norte, Nordeste, Centro-Oeste, Sudeste e Sul formam as 5 regiões!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "geo_10",
            question = "Qual é o maior oceano do nosso planeta?",
            options = listOf("Atlântico", "Pacífico", "Índico", "Glacial Ártico"),
            correctAnswer = "Pacífico",
            explanation = "O Oceano Pacífico é tão grande que cobre um terço da superfície da Terra!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "geo_11",
            question = "Como é chamada a linha imaginária que divide a Terra em hemisfério Norte e Sul?",
            options = listOf("Trópico de Câncer", "Linha do Equador", "Meridiano de Greenwich", "Círculo Polar"),
            correctAnswer = "Linha do Equador",
            explanation = "A Linha do Equador passa bem no meio da cintura do planeta Terra!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "geo_12",
            question = "Qual dos quatro pontos cardeais fica na direção onde o Sol nasce pela manhã?",
            options = listOf("Norte", "Sul", "Leste", "Oeste"),
            correctAnswer = "Leste",
            explanation = "O Sol sempre surge a Leste e se põe a Oeste!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "geo_13",
            question = "Qual é o oceano que banha todo o litoral do Brasil?",
            options = listOf("Oceano Pacífico", "Oceano Atlântico", "Oceano Índico", "Oceano Ártico"),
            correctAnswer = "Oceano Atlântico",
            explanation = "Todas as praias brasileiras são banhadas pelas águas do Oceano Atlântico!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "geo_14",
            question = "Qual é o maior rio em volume de água do planeta Terra?",
            options = listOf("Rio Nilo", "Rio Amazonas", "Rio São Francisco", "Rio Paraná"),
            correctAnswer = "Rio Amazonas",
            explanation = "O Rio Amazonas tem o maior volume de água e fica na América do Sul!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),

        // 10 anos
        Question(
            id = "geo_15",
            question = "Em qual continente fica o Brasil e a Argentina?",
            options = listOf("Europa", "Ásia", "América do Sul", "África"),
            correctAnswer = "América do Sul",
            explanation = "Brasil e Argentina fazem parte do continente sul-americano!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "geo_16",
            question = "Qual movimento da Terra ao redor de si mesma causa o dia e a noite?",
            options = listOf("Translação", "Rotação", "Nutação", "Órbita"),
            correctAnswer = "Rotação",
            explanation = "A rotação demora 24 horas e faz o Sol parecer cruzar o céu gerando o dia e a noite!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "geo_17",
            question = "Qual é a montanha mais alta do mundo acima do nível do mar?",
            options = listOf("Monte Everest", "Pico da Neblina", "Monte Fuji", "Cordilheira dos Andes"),
            correctAnswer = "Monte Everest",
            explanation = "O Monte Everest tem mais de 8.848 metros de altura na Cordilheira do Himalaia!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "geo_18",
            question = "Qual é o maior deserto quente do mundo?",
            options = listOf("Deserto do Saara", "Deserto de Atacama", "Deserto de Gobi", "Deserto da Arábia"),
            correctAnswer = "Deserto do Saara",
            explanation = "O Deserto do Saara fica no norte da África e é imenso!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "geo_19",
            question = "Qual é o ponto mais alto do relevo brasileiro?",
            options = listOf("Pico da Bandeira", "Pico da Neblina", "Pão de Açúcar", "Pico das Agulhas Negras"),
            correctAnswer = "Pico da Neblina",
            explanation = "O Pico da Neblina fica no Amazonas e tem quase 3.000 metros de altitude!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "geo_20",
            question = "Quantos estados compõem o Brasil, incluindo o Distrito Federal?",
            options = listOf("20 estados", "24 estados", "26 estados e 1 DF", "30 estados"),
            correctAnswer = "26 estados e 1 DF",
            explanation = "O Brasil é formado por 26 estados mais o Distrito Federal, totalizando 27 unidades federativas!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "geo_21",
            question = "Como é chamada a representação gráfica plana e reduzida da superfície da Terra?",
            options = listOf("Globo", "Mapa", "Fotografia", "Croqui"),
            correctAnswer = "Mapa",
            explanation = "Um mapa é a representação plana e em escala de uma parte ou do todo do planeta!",
            subject = Subject.GEOGRAFIA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),

        // ================= HISTÓRIA (21 Perguntas) =================
        // 6-7 anos
        Question(
            id = "his_1",
            question = "Quem já vivia no Brasil antes da chegada dos portugueses?",
            options = listOf("Os povos indígenas", "Os astronautas", "Os robôs", "Os vikings"),
            correctAnswer = "Os povos indígenas",
            explanation = "Milhares de povos indígenas já moravam aqui com rica cultura e florestas!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "his_2",
            question = "Qual desses animais gigantes viveu na Terra há milhões de anos antes dos humanos?",
            options = listOf("Dinossauro", "Gato", "Canguru", "Cavalo"),
            correctAnswer = "Dinossauro",
            explanation = "Os dinossauros dominaram a Terra em tempos pré-históricos incríveis!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "his_3",
            question = "Antigamente, as pessoas desenhavam nas paredes de cavernas. Como chamamos esses desenhos?",
            options = listOf("Pinturas rupestres", "Fotografias", "Grafites modernos", "Cartazes"),
            correctAnswer = "Pinturas rupestres",
            explanation = "Pinturas rupestres contavam histórias de caça e animais na pré-história!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "his_4",
            question = "Que tipo de transporte com grandes velas os navegadores antigos usavam para cruzar o mar?",
            options = listOf("Caravelas", "Aviões", "Metrôs", "Motos"),
            correctAnswer = "Caravelas",
            explanation = "As caravelas eram barcos movidos pela força do vento em suas velas!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "his_5",
            question = "Qual objeto os humanos inventaram há muito tempo que facilitou rodar carroças e transportar coisas?",
            options = listOf("A roda", "O computador", "O celular", "O plástico"),
            correctAnswer = "A roda",
            explanation = "A invenção da roda revolucionou o transporte de cargas e pessoas no mundo!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "his_6",
            question = "Onde reis, rainhas, príncipes e princesas costumavam viver na Idade Média?",
            options = listOf("Castelos", "Iglus", "Prédios de vidro", "Ocas"),
            correctAnswer = "Castelos",
            explanation = "Castelos com torres de pedra e fossos protegiam os reinos antigos!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "his_7",
            question = "Antes das luzes elétricas de hoje, o que as pessoas acendiam para enxergar de noite?",
            options = listOf("Velas e tochas", "Lanternas laser", "Telas de tablet", "Postes solares"),
            correctAnswer = "Velas e tochas",
            explanation = "As pessoas usavam fogo em velas, tochas e lampiões a óleo!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),

        // 8-9 anos
        Question(
            id = "his_8",
            question = "Em que ano a esquadra de Pedro Álvares Cabral chegou ao litoral do Brasil?",
            options = listOf("1500", "1822", "1900", "2000"),
            correctAnswer = "1500",
            explanation = "A chegada dos navegadores portugueses aconteceu no ano de 1500!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "his_9",
            question = "Quem foi o famoso inventor brasileiro conhecido por criar o avião 14-Bis?",
            options = listOf("Santos Dumont", "Tiradentes", "Machado de Assis", "Dom Pedro I"),
            correctAnswer = "Santos Dumont",
            explanation = "Alberto Santos Dumont realizou o primeiro voo homologado com o 14-Bis!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "his_10",
            question = "Qual árvore nativa muito valorizada deu origem ao nome do nosso país Brasil?",
            options = listOf("Pau-brasil", "Ipê-amarelo", "Jacarandá", "Castanheira"),
            correctAnswer = "Pau-brasil",
            explanation = "O Pau-brasil fornecia uma tinta vermelha brilhante cor de brasa!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "his_11",
            question = "Em que civilização antiga foram construídas as famosas Pirâmides de Gizé e a Esfinge?",
            options = listOf("Egito Antigo", "Grécia Antiga", "Roma Antiga", "Japão Antigo"),
            correctAnswer = "Egito Antigo",
            explanation = "Os faraós do Egito Antigo construíram as monumentais pirâmides de pedra!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "his_12",
            question = "Em que data comemoramos a Proclamação da Independência do Brasil?",
            options = listOf("7 de Setembro", "15 de Novembro", "25 de Dezembro", "1 de Maio"),
            correctAnswer = "7 de Setembro",
            explanation = "No 7 de Setembro de 1822 foi proclamada a independência às margens do Ipiranga!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "his_13",
            question = "Qual guerreiro usava armadura de metal e montava a cavalo nos reinos da Idade Média?",
            options = listOf("Cavaleiro medieval", "Gladiador romano", "Ninja", "Samurai"),
            correctAnswer = "Cavaleiro medieval",
            explanation = "Os cavaleiros protegiam castelos com lanças, escudos e armaduras!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "his_14",
            question = "Qual invenção de Johannes Gutenberg permitiu imprimir muitos livros rapidamente?",
            options = listOf("A Prensa de tipos móveis", "A Máquina fotográfica", "O Telégrafo", "O Rádio"),
            correctAnswer = "A Prensa de tipos móveis",
            explanation = "A imprensa de Gutenberg permitiu espalhar conhecimento pelo mundo em livros!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),

        // 10 anos
        Question(
            id = "his_15",
            question = "Quem foi o líder do Quilombo dos Palmares e herói da resistência no Brasil?",
            options = listOf("Zumbi dos Palmares", "Aleijadinho", "Dom João VI", "José Bonifácio"),
            correctAnswer = "Zumbi dos Palmares",
            explanation = "Zumbi dos Palmares foi o grande líder que lutou pela liberdade!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "his_16",
            question = "Qual princesa assinou a Lei Áurea em 1888, que aboliu a escravidão no Brasil?",
            options = listOf("Princesa Isabel", "Princesa Leopoldina", "Rainha Elizabeth", "Princesa Diana"),
            correctAnswer = "Princesa Isabel",
            explanation = "A Princesa Isabel assinou a histórica Lei Áurea em 13 de maio de 1888!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "his_17",
            question = "Em qual país nasceram os Jogos Olímpicos na Antiguidade?",
            options = listOf("Grécia", "Roma", "Egito", "China"),
            correctAnswer = "Grécia",
            explanation = "Os primeiros Jogos Olímpicos aconteceram em Olímpia, na Grécia Antiga!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "his_18",
            question = "Qual presidente brasileiro liderou a construção da nova capital, Brasília?",
            options = listOf("Juscelino Kubitschek", "Getúlio Vargas", "Deodoro da Fonseca", "Floriano Peixoto"),
            correctAnswer = "Juscelino Kubitschek",
            explanation = "JK construiu e inaugurou Brasília em 1960 com o plano '50 anos em 5'!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "his_19",
            question = "Qual civilização antiga construiu o famoso Coliseu e aquedutos monumentais?",
            options = listOf("Roma Antiga", "Vikings", "Astecas", "Sumérios"),
            correctAnswer = "Roma Antiga",
            explanation = "O Império Romano ergueu grandes estradas, arcos e o Coliseu em Roma!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "his_20",
            question = "Em que ano o ser humano pisou pela primeira vez na Lua (missão Apollo 11)?",
            options = listOf("1969", "1945", "1989", "2001"),
            correctAnswer = "1969",
            explanation = "Em julho de 1969, Neil Armstrong deu o primeiro passo histórico na Lua!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "his_21",
            question = "Qual foi o primeiro presidente da República do Brasil em 1889?",
            options = listOf("Marechal Deodoro da Fonseca", "Tiradentes", "Rui Barbosa", "Prudente de Morais"),
            correctAnswer = "Marechal Deodoro da Fonseca",
            explanation = "O Marechal Deodoro da Fonseca proclamou a República em 15 de novembro de 1889!",
            subject = Subject.HISTORIA,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),

        // ================= CONHECIMENTOS GERAIS (21 Perguntas) =================
        // 6-7 anos
        Question(
            id = "ger_1",
            question = "Quantos dias tem uma semana inteira?",
            options = listOf("5 dias", "6 dias", "7 dias", "10 dias"),
            correctAnswer = "7 dias",
            explanation = "Segunda, terça, quarta, quinta, sexta, sábado e domingo: são 7 dias!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "ger_2",
            question = "Quantas cores tem um arco-íris no céu?",
            options = listOf("3 cores", "5 cores", "7 cores", "12 cores"),
            correctAnswer = "7 cores",
            explanation = "Vermelho, laranja, amarelo, verde, azul, anil e violeta: 7 cores!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "ger_3",
            question = "Qual animal é conhecido carinhosamente como o 'melhor amigo do homem'?",
            options = listOf("Cachorro", "Gato", "Papagaio", "Tartaruga"),
            correctAnswer = "Cachorro",
            explanation = "O cão é leal, companheiro e adora brincar com as pessoas!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "ger_4",
            question = "Qual cor do semáforo indica que podemos atravessar com segurança na faixa?",
            options = listOf("Vermelho", "Amarelo", "Verde", "Azul"),
            correctAnswer = "Verde",
            explanation = "Verde significa que podemos seguir, e vermelho significa parar!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "ger_5",
            question = "Qual das estações do ano é conhecida como a estação mais quente e de férias na praia?",
            options = listOf("Inverno", "Verão", "Outono", "Primavera"),
            correctAnswer = "Verão",
            explanation = "No verão os dias são mais longos, quentes e ensolarados!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "ger_6",
            question = "O que as abelhas produzem na colmeia que é docinho e gostoso?",
            options = listOf("Mel", "Chocolate", "Suco", "Sorvete"),
            correctAnswer = "Mel",
            explanation = "As abelhinhas colhem o néctar das flores e fazem o delicioso mel!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),
        Question(
            id = "ger_7",
            question = "Quantas horas tem um dia completo (com o dia e a noite)?",
            options = listOf("12 horas", "18 horas", "24 horas", "30 horas"),
            correctAnswer = "24 horas",
            explanation = "Um dia completo tem exatamente 24 horas!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.FACIL,
            ageRange = "6-7"
        ),

        // 8-9 anos
        Question(
            id = "ger_8",
            question = "Qual instrumento musical tem teclas pretas e brancas?",
            options = listOf("Violão", "Flauta", "Piano", "Bateria"),
            correctAnswer = "Piano",
            explanation = "O piano possui teclas brancas e pretas que tocam belas melodias!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "ger_9",
            question = "Qual é o maior animal vivo de todo o planeta Terra?",
            options = listOf("Elefante Africano", "Baleia-Azul", "Tubarão-Branco", "Girafa"),
            correctAnswer = "Baleia-Azul",
            explanation = "A baleia-azul pode medir até 30 metros de comprimento e pesar 150 toneladas!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "ger_10",
            question = "Qual esporte é praticado com uma bola redonda e 11 jogadores de cada lado num gramado?",
            options = listOf("Basquete", "Futebol", "Vôlei", "Tênis"),
            correctAnswer = "Futebol",
            explanation = "O futebol é a paixão nacional com dois times de 11 jogadores e dois gols!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "ger_11",
            question = "Quantos meses tem um ano inteiro?",
            options = listOf("10 meses", "12 meses", "14 meses", "16 meses"),
            correctAnswer = "12 meses",
            explanation = "De Janeiro a Dezembro somam-se 12 meses no calendário!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "ger_12",
            question = "O que devemos fazer para manter os dentes fortes e saudáveis após as refeições?",
            options = listOf("Escovar os dentes", "Comer balas", "Dormir logo", "Tomar refrigerante"),
            correctAnswer = "Escovar os dentes",
            explanation = "A escovação correta e o uso de fio dental evitam cáries e protegem os dentes!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "ger_13",
            question = "Qual animal é conhecido por mudar de cor para se camuflar na natureza?",
            options = listOf("Camaleão", "Tigre", "Pinguim", "Cavalo"),
            correctAnswer = "Camaleão",
            explanation = "O camaleão muda de cor para se esconder e se comunicar!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),
        Question(
            id = "ger_14",
            question = "Qual é o metal precioso de cor amarela muito usado para fazer alianças e coroas?",
            options = listOf("Prata", "Ouro", "Ferro", "Bronze"),
            correctAnswer = "Ouro",
            explanation = "O ouro é um metal nobre amarelo brilhante de alto valor!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.MEDIO,
            ageRange = "8-9"
        ),

        // 10 anos
        Question(
            id = "ger_15",
            question = "Qual idioma oficial é falado na maior parte dos países vizinhos ao Brasil na América do Sul?",
            options = listOf("Espanhol", "Inglês", "Francês", "Italiano"),
            correctAnswer = "Espanhol",
            explanation = "Países como Argentina, Chile, Colômbia e Peru têm o Espanhol como língua oficial!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "ger_16",
            question = "Qual peça do jogo de Xadrez pode se mover em qualquer direção e quantas casas quiser?",
            options = listOf("O Rei", "A Rainha (Dama)", "O Bispo", "O Cavalo"),
            correctAnswer = "A Rainha (Dama)",
            explanation = "A Rainha é a peça mais poderosa do tabuleiro de xadrez!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "ger_17",
            question = "Qual é a velocidade aproximada da luz no vácuo?",
            options = listOf("300 km/h", "3.000 km/s", "300.000 km/s", "1.000.000 km/s"),
            correctAnswer = "300.000 km/s",
            explanation = "A luz viaja a incríveis 300 mil quilômetros a cada segundo!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "ger_18",
            question = "Quem pintou o famoso quadro 'Mona Lisa' com seu sorriso misterioso?",
            options = listOf("Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh", "Michelangelo"),
            correctAnswer = "Leonardo da Vinci",
            explanation = "O mestre renascentista italiano Leonardo da Vinci pintou a célebre Mona Lisa!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "ger_19",
            question = "Quantos anéis entrelaçados formam o símbolo oficial dos Jogos Olímpicos?",
            options = listOf("3 anéis", "4 anéis", "5 anéis", "6 anéis"),
            correctAnswer = "5 anéis",
            explanation = "Os 5 anéis coloridos representam os continentes do mundo unidos pelo esporte!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "ger_20",
            question = "Qual é o principal gás responsável pelo efeito estufa gerado pela queima de combustíveis fósseis?",
            options = listOf("Dióxido de Carbono (CO2)", "Oxigênio", "Nitrogênio", "Hélio"),
            correctAnswer = "Dióxido de Carbono (CO2)",
            explanation = "O CO2 retém calor na atmosfera e o excesso aquece o planeta!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        ),
        Question(
            id = "ger_21",
            question = "Qual é o nome do animal mais rápido em corrida na terra, atingindo mais de 100 km/h?",
            options = listOf("Guepardo (Chita)", "Leão", "Cavalo Selvagem", "Canguru"),
            correctAnswer = "Guepardo (Chita)",
            explanation = "O guepardo pode acelerar de 0 a 100 km/h em apenas 3 segundos!",
            subject = Subject.CONHECIMENTOS_GERAIS,
            difficulty = Difficulty.DIFICIL,
            ageRange = "10"
        )
    )

    fun getQuestionsFor(
        subject: Subject,
        age: Int,
        count: Int = 10,
        reinforceTopic: String? = null
    ): List<Question> {
        val targetAgeRange = when {
            age <= 7 -> "6-7"
            age <= 9 -> "8-9"
            else -> "10"
        }

        val pool = if (subject == Subject.DESAFIO_PIPO) {
            allQuestions.filter { it.ageRange == targetAgeRange }
                .ifEmpty { allQuestions }
        } else {
            val matchingSubject = allQuestions.filter { it.subject == subject }
            val matchingAge = matchingSubject.filter { it.ageRange == targetAgeRange }
            if (matchingAge.size >= count) matchingAge else matchingSubject
        }

        // Shuffle and prioritize questions matching reinforcement topic if any
        val sortedPool = if (!reinforceTopic.isNullOrBlank()) {
            pool.sortedByDescending { q ->
                q.question.contains(reinforceTopic, ignoreCase = true) ||
                q.explanation.contains(reinforceTopic, ignoreCase = true)
            }
        } else {
            pool.shuffled()
        }

        return sortedPool.take(count).ifEmpty {
            allQuestions.shuffled().take(count)
        }
    }

    fun getAllCount(): Int = allQuestions.size
}

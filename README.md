# PIPO — Aprender brincando 🚀

O **PIPO** é uma plataforma educacional gamificada desenvolvida para crianças de **6 a 10 anos**. Combinando aprendizado lúdico com perguntas e respostas educativas, inteligência artificial Google Gemini, sistema de XP, níveis, sequências de acertos (streak) e conquistas, o PIPO transforma a curiosidade natural das crianças em conhecimento divertido.

---

## 🌟 O Mascote Pipo

O **Pipo** é uma criatura simpática e expressiva de corpo arredondado, antenas brilhantes e sorriso contagiante. Ele nunca julga nem repreende; ele acompanha, vibra com os acertos ("Boa!", "Mandou muito bem!") e acolhe os erros ("Quase! Vamos pensar juntos.", "Você está aprendendo!").

Possui 8 estados animados na interface:
- **IDLE** (amigável e atento)
- **HAPPY** (sorridente com olhinhos brilhantes)
- **CELEBRATING** (braços no alto em vitória)
- **THINKING** (mãozinha no queixo pensando no desafio)
- **CURIOUS** (cabeça inclinada investigando o mundo)
- **ENCOURAGING** (piscadela carinhosa e acolhedora)
- **SURPRISED** (boca em 'o' de encantamento)
- **VICTORY** (comemoração com confetes e medalhas)

---

## 🎯 Faixas Etárias e Matérias

- **6 a 7 anos**: Alfabetização, números, soma, subtração, formas, cores, animais e natureza.
- **8 a 9 anos**: Multiplicação, divisão, interpretação, gramática, ciências, geografia e história.
- **10 anos**: Problemas matemáticos, interpretação textual, raciocínio lógico e desafios gerais.

### Matérias disponíveis:
1. 📚 **Português** (rimas, palavras, gramática e leitura)
2. 🔢 **Matemática** (contas, tabuada, problemas e formas)
3. 🔬 **Ciências** (animais, corpo humano, plantas e universo)
4. 🌎 **Geografia** (planeta Terra, mapas, relevo e natureza)
5. 📖 **História** (invenções, civilizações e grandes momentos)
6. 🧠 **Conhecimentos Gerais** (curiosidades e descobertas do dia a dia)
7. 🎲 **Desafio Pipo** (mistura dinâmica de todas as matérias com bônus de XP)

---

## 🎮 Gamificação

- **+10 XP** por resposta correta.
- **Bônus de Sequência (Streak 🔥)**:
  - 3 acertos seguidos: **+5 XP**
  - 5 acertos seguidos: **+10 XP**
  - 10 acertos seguidos: **+25 XP**
- **Níveis de Evolução**:
  - Nível 1: Curioso 🌱 (0 – 50 XP)
  - Nível 2: Descobridor 🔎 (51 – 150 XP)
  - Nível 3: Aprendiz ⚡ (151 – 300 XP)
  - Nível 4: Mestre 🧠 (301 – 600 XP)
  - Nível 5: Gênio 💡 (601+ XP)
- **Conquistas Desbloqueáveis (🏆)**:
  - 🏆 *Primeiro Quiz*
  - ⭐ *10 Acertos*
  - 🔥 *Em Chamas*
  - 🧠 *Mente Curiosa*
  - 🚀 *Subindo*
  - 🎯 *Perfeito*
  - 🤖 *Amigo da IA*
  - 👑 *Campeão Pipo*

---

## 🤖 Integração com Google Gemini

O aplicativo utiliza a API do **Google Gemini** (`gemini-3.5-flash`) para enriquecer dinamicamente perguntas educativas sob demanda de forma segura e contextualizada para a idade da criança.

- **Mecanismo de Fallback 100% Offline**: Caso o Gemini esteja indisponível, sem rede ou a chave não esteja configurada, o aplicativo utiliza automaticamente o banco local com mais de 125 perguntas criteriosamente elaboradas por educadores. A criança **nunca** vê uma tela de erro técnico.

---

## 🛠️ Tecnologias Utilizadas

- **Kotlin** & **Jetpack Compose** (Material 3)
- **Coroutines** & **StateFlow** (Arquitetura MVVM limpa)
- **Retrofit**, **OkHttp** & **Moshi** para chamadas de API Gemini
- **Text-To-Speech (TTS)** nativo em Português brasileiro para acessibilidade infantil
- **ToneGenerator** para sintetização de efeitos sonoros lúdicos
- **Vector Canvas Animations** para o mascote Pipo e efeitos de confetes
- **SharedPreferences** para persistência local segura (sem coleta de dados pessoais sensíveis)

---

## ⚙️ Variáveis de Ambiente & Instalação

1. Duplique o arquivo `.env.example` para `.env`:
   ```bash
   cp .env.example .env
   ```
2. Adicione sua chave do Google Gemini no `.env`:
   ```env
   GEMINI_API_KEY=sua_chave_aqui
   ```
3. Compile e execute o projeto Android via Gradle:
   ```bash
   gradle assembleDebug
   ```

# Simple Bank

Um aplicativo que utiliza MVVM com módulos que tratam diferentes contextos dentro do código.
Esse app mostra um fluxo simples de um app de banco. Com dados de saldo, fatura e cartão de crédito.
Utiliza também um scanner que se comunica com um server, que retornar dados fakes para prosseguir
com o pagamento.

Figma: https://www.figma.com/file/heTuvjTahRdQwb9v91MksM/Mobile-App?type=design&node-id=0%3A1&mode=design&t=60ZjZqpqI8t4mwKA-1

## :app
Módulo que contém todas as implementações referente à IU e comunicação com a camada do repo
## :ds
Módulo responsável por tudo que lida com o sistema de design. Cores, textos, tamanhos e componentes.
## :sdk
Módulo responsável por componentes facilitadores, camada de dados.
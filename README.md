# FilosofosFinal
Projeto Final Filosofos
Relatório
O problema do
Jantar dos Filósofos
, proposto por Dijkstra, descreve cinco filósofos que alternam entre pensar e comer. Para comer, cada filósofo precisa de
dois garfos
(um à esquerda e um à direita). Cada garfo é um recurso compartilhado por dois filósofos adjacentes.
O impasse surge porque, se todos os filósofos pegarem simultaneamente seu garfo esquerdo, nenhum terá acesso ao garfo direito disponível. Todos ficam presos esperando — ocorre então um
deadlock
.
/n
O que gera o impasse?
O impasse ocorre devido ao atendimento simultâneo de todas as
4 condições de Coffman
necessárias para deadlock:1.
Exclusão mútua
Cada garfo só pode ser usado por um filósofo por vez.2.
Posse e espera (hold & wait)
Cada filósofo pega um garfo (recurso) e espera pelo outro.3.
Não-preempção
Um garfo não pode ser tirado de um filósofo — ele só o libera voluntariamente.4.
Espera circular
Cada filósofo espera pelo garfo do outro, formando um ciclo fechado:
F1 → F2 → F3 → F4 → F5 → F1.
O deadlock só acontece se
todas
as quatro condições forem satisfeitas simultaneamente.
Fluxograma
Projeto Final Filosofos 1
processo Filosofo(i):while(True): PENSAR() // identificar a ordem dos recursos (evita espera circular) garfo1 = min(i, (i+1) mod N) garfo2 = max(i, (i+1) mod N) // tentar adquirir os recursos sempre na mesma ordem pegar(garfo1) pegar(garfo2) //Utiliza recurso COMER() // liberar os recursos liberar(garfo2) liberar(garfo1)
Explicação da estratégia
A correção aplica a técnica de
hierarquia de recursos
, na qual todos os locks devem ser adquiridos seguindo sempre a mesma ordenação global. Essa regra impede que uma thread possa estar aguardando por um lock que outra adquiriu fora da ordem permitida, o que elimina a condição de
espera circular
, uma das quatro condições de Coffman necessárias para o deadlock. Sem espera circular, o deadlock não pode ocorrer. Essa solução é equivalente à usada no problema do Jantar dos Filósofos, no qual cada filósofo deve pegar sempre primeiro o garfo de menor id antes do de maior id, garantindo que o grafo de dependências seja sempre acíclico.
Projeto Final Filosofos 2

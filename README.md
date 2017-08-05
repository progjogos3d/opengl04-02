# Aula 04 - Exemplo de câmera completo.

Neste exemplo, foi criado no pacote `br.pucpr.cg` a classe `Camera`.

A implementação da camera é feita por 2 matrizes:

* View: Contém informações relacionadas a localização e orientação da câmera: posição, local para onde olha e vetor 
  apontando para "cima".
* Projection: Contém informações relacionadas a projeção da câmera: Angulo de abertura taxa de aspecto, planos near e 
  far (que delimitam o quao próximo e afastado a camera enxerga).
  
A classe da camera gera as matrizes com base em dois métodos da classe `Matrix4f` da biblioteca `LWJGL`:
 
 * `lookAt`: Para a matriz view
 * `perspective`: Para a matriz de projeção. A classe `Camera` não contempla câmera com projeção ortogonal, embora o 
   método para a geração desse tipo de perspectiva esteja presente na LWJGL.

Para a taxa de aspecto, solicitamos a OpenGL informações sobre o Viewport e a calculamos automaticamente. Em muitos 
tutoriais, utiliza-se diretamente o tamanho da janela, porém, monitores retina display (Mac e celulares) podem 
possuir dimensões diferentes que as da janela, por isso esse método é recomendado.
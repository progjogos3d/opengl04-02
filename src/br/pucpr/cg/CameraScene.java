package br.pucpr.cg;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import br.pucpr.mage.*;
import org.joml.Matrix4f;

public class CameraScene implements Scene {
    private Keyboard keys = Keyboard.getInstance();
    
    private Mesh mesh;
    private float angle;

    //Criamos a variável da câmera
    //Ela utilizará a posição padrão (0,0,2) e perspectiva com 60 graus de abertura
    private Camera camera = new Camera();

    @Override
    public void init() {
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        mesh = MeshFactory.createCube();

        //Experimente afastar a camera um pouco para cima para melhorar a visualização do cubo
        //camera.getPosition().y = 1.0f;
    }

    @Override
    public void update(float secs) {
        if (keys.isPressed(GLFW_KEY_ESCAPE)) {
            glfwSetWindowShouldClose(glfwGetCurrentContext(), true);
            return;
        }

        angle += Math.toRadians(10) * secs;
    }

    @Override
    public void draw() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        //Definimos a camera no shader da malha
        Shader shader = mesh.getShader();
        shader.bind();
        camera.apply(shader);
        shader.unbind();

        //Definimos a transformação de mundo da malha
        mesh.setUniform("uWorld", new Matrix4f().rotateX(0.3f).rotateY(angle));
        mesh.draw();
    }

    @Override
    public void deinit() {
    }

    public static void main(String[] args) {
        new Window(new CameraScene(), "Rotating cube", 800, 600).show();
    }
}

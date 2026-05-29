/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistembangungeo;

/**
 *
 * @author morxidia
 */
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public abstract class GeometryTask implements Runnable {

    protected int queueNumber;
    protected JTextArea outputArea;

    public GeometryTask(int queueNumber, JTextArea outputArea) {
        this.queueNumber = queueNumber;
        this.outputArea = outputArea;
    }

    // Every distinct shape subclass must implement these two methods
    protected abstract String getShapeName();

    protected abstract String calculateResult();

    @Override
    public void run() {
        // 1. Instantly log state when the thread starts executing
        SwingUtilities.invokeLater(() -> {
            outputArea.append("-> Start geometry thread - " + queueNumber + " (" + getShapeName() + ")\n");
        });

        // Simulating processing race variances
        try {
            Thread.sleep((long) (Math.random() * 800) + 200);
        } catch (InterruptedException ignored) {
        }

        String resultData = calculateResult();

        // 2. Log state when finished (interrupting out of order)
        SwingUtilities.invokeLater(() -> {
            outputArea.append("   [FINISH] Thread - " + queueNumber + " (" + getShapeName() + ") | " + resultData + "\n");
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
        });
    }
}

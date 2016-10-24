package vls;

import ch.qos.logback.core.AppenderBase;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
 
public class TextAreaAppender<E> extends AppenderBase<E> {
 
    private static volatile TextArea textArea = null;
    
    public TextAreaAppender()
    {
    }
 
    public static void setTextArea(TextArea area)
    {
    	textArea = area;
    }
    
	@Override
	protected void append(E eventObject)
	{
        final String message = eventObject.toString();
        // Append formatted message to text area using the Thread.
        try {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (textArea != null) {
                            if (textArea.getText().length() == 0) {
                                textArea.setText(message+"\n");
                            } else {
                                textArea.selectEnd();
                                textArea.insertText(textArea.getText().length(),
                                        message+"\n");
                            }
                        }
                    } catch (final Throwable t) {
                        System.out.println("Unable to append log to text area: "
                                + t.getMessage());
                    }
                }
            });
        } catch (final IllegalStateException e) {
            // ignore case when the platform hasn't yet been iniitialized
        }
	}
}
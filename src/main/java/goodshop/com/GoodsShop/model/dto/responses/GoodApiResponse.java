package goodshop.com.GoodsShop.model.dto.responses;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
public class GoodApiResponse<T> {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private Collection<T> data;
    public GoodApiResponse() {
        this.timestamp = LocalDateTime.now();
    }
    public GoodApiResponse(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }


}

//package dev.kienntt.demo.BE_Vinpearl.config;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import dev.kienntt.demo.BE_Vinpearl.model.Comment;
//import dev.kienntt.demo.BE_Vinpearl.model.Customer;
//import dev.kienntt.demo.BE_Vinpearl.model.Post;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.io.IOException;
//import java.util.List;
//
//public class CommentWebSocketHandler extends TextWebSocketHandler {
//
//    private final SimpMessagingTemplate simpMessagingTemplate;
//
//    public CommentWebSocketHandler(SimpMessagingTemplate simpMessagingTemplate) {
//        this.simpMessagingTemplate = simpMessagingTemplate;
//    }
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        // Khi kết nối WebSocket được thiết lập, gửi danh sách các bình luận hiện có tới client
//        List<Comment> comments = getCommentsForPost(POST_ID);
//        simpMessagingTemplate.convertAndSend("/topic/comments/" + POST_ID, comments);
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        // Khi client gửi tin nhắn mới, tạo đối tượng Comment từ tin nhắn đó và lưu vào CSDL
//        Comment comment = createCommentFromMessage(message);
//        saveComment(comment);
//
//        // Gửi thông báo tới các client đang lắng nghe kênh "/topic/comments/{post-id}"
//        simpMessagingTemplate.convertAndSend("/topic/comments/" + POST_ID, comment);
//    }
//
//    private List<Comment> getCommentsForPost(Long postId) {
//        // Lấy danh sách các bình luận của bài viết có postId từ CSDL
//        return commentService.getCommentsByPostId(postId);
//    }
//
//    private Comment createCommentFromMessage(TextMessage message) throws IOException {
//        // Tạo đối tượng Comment từ tin nhắn JSON
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode jsonNode = mapper.readTree(message.getPayload());
//        Long customerId = jsonNode.get("customerId").asLong();
//        String content = jsonNode.get("content").asText();
////        Customer customer = customerService.getCustomerById(customerId);
////        Post post = postService.getPostById(POST_ID);
//        return new Comment(content, post, customerId);
//    }
//
//    private void saveComment(Comment comment) {
//        // Lưu bình luận vào CSDL
//        commentService.saveComment(comment);
//    }
//}
#version 150

uniform sampler2D sampler0;
in vec2 texCoord;
out vec4 fragColor;

void main() {
    vec4 color = texture(sampler0, texCoord);
    // Inverti i colori come gli Enderman
    fragColor = vec4(1.0 - color.rgb, color.a);
}
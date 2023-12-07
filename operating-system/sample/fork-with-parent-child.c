#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main() {
  pid_t pid;

  // Create a new process
  pid = fork();

  if (pid < 0) {
    fprintf(stderr, "Fork failed !");
    return 1;
  } else if (pid == 0) {
    // Çocuk süreç
    printf("Child process is coming from the parent process\n");
  } else {
    // Ebeveyn süreç
    printf("Coming from parent process\n");
  }

  return 0;
}
